---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# AB-3 Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `+ /name Tan Ah Gao /id chihuahua69 /hp 999 /tag finance`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("- id abc123")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is first passed to an `AccountManagerParser` object which in turn creates a parser that matches the command (e.g., `LoginCommandParser`) and uses it to parse the command.
1. If the parsing result returned by `AccountManagerParser` is null, the command is then passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AccountManagerParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `LoginCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `LoginCommand`) which the `AccountManagerParser` returns back as a `Command` object.
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the tag data i.e., all `Tag` objects (which are contained in a `TagList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data, user preference data and tag list data in JSON format, and read them back into corresponding objects.
* inherits from `AddressBookStorage`, `UserPrefStorage` and `TagListStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Implemented\] Edit feature

#### Current Implementation

The edit mechanism was previously based on the index of the contact in the shown list of contacts,
but is now fitted to serve our new key identifier of an employee, namely the ID.
In addition, the ID of an employee now cannot be edited by any means (intuitive logic of unique ID),
hence any ID changes would mean that the employee needs to be deleted and added again with their new ID and information.

<puml src="diagrams/EditCommandFlow.puml" alt="EditCommandFlow" />

#### Kept changes from previous implementation
- Optional to edit all fields, at least 1 field is enough
- Fields can be entered in any order

#### Pros of ID as key
- Ensure that random employee details are not edited by accident
- Every edit command is now more purposeful as if ID is not found, edit does not go through

#### Cons of ID as key
- Slightly slower to type in than index
- Slightly slower to execute if employee list shown is huge



### \[Implemented\] Undo/redo feature

Our undo/redo mechanism is facilitated by a `CommandList` inside the `Model` and an internal pointer to track the current command, offering a command-centric approach, which can be highly efficient and flexible.<br><br>

**Core Components**
1. `CommandList`: This list stores all the commands that have been executed. Each command in this list should be capable of reversing its effect (undo) and reapplying its effect (redo).

2. `CommandList.currentCommandIndex`: This pointer indicates the current position in the commandList. It helps determine which commands have been executed and which ones have been undone.

**Functionalities**
1. Executing a command:
   * When a new command is executed, it is added to the end of the commandList.
   * The current command pointer is moved to this new command's position.
   * Commands following the current command pointer (if any) are cleared from the list, as the new command creates a new "branch" of history, invalidating the redo history.<br><br>

2. Undoing a command:
   * To undo, check if the pointer is not at the beginning of the list.
   * The command at the current pointer position knows how to undo itself. It executes its undo method.
   * The pointer is then decremented, moving backwards in the command list.<br><br>

3. Redoing a command:
   * To redo, check if the pointer is not at the end of the list.
   * Increment the pointer to move to the next command in the list.
   * The command at this new pointer position knows how to redo itself and executes its redo method.<br><br>

4. Self-contained commands:
   * Each command object contains all the information necessary to both apply and reverse its effects. When a command is executed, it also prepares for its potential undo by storing any relevant state changes or backups of the data it modifies. Currently our undo/redo only supports the following commands.
   * Add: Stores a reference to the added person. To undo, it removes this person from the address book. To redo, it simply re-adds the stored person to the address book.
   * Delete: Stores a reference to the deleted person. To undo, it re-adds this person to the address book using the stored details. To redo, is removes the person again from the address book.
   * Edit: Stores a reference to the person before edit and another reference to the person after edit. To undo, it removes the person after edit and re-adds the person before edit. To redo, it removes the person before edit and re-adds the person after edit.
   * Clear: Stores a reference to the address book before clearing. To undo, it restores the address book to the state before clearing. To redo, it clears the address book again.


#### Design considerations:

Compared with the recommended way that stores the entire address book for each undo/redo, our command-centric approach has the following pros and cons:

**Pros:**

* Reduced Memory Usage: Since only changes are stored rather than the entire application state, this method typically consumes less memory. For instance, the delete command only needs to store the details of the deleted person, not the state of the entire address book.

* Scalability: As operations increase in complexity or frequency, storing individual command effects rather than snapshots of the entire state scales better. This makes the system more efficient and responsive as the volume of data grows.

* Flexibility and Modularity: Commands encapsulate their functionality, making the system more modular. This separation allows for easier updates and modifications to individual commands without affecting the overall system.

* Targeted Undo/Redo: This approach allows for precise control over what gets undone or redone, making the operations more predictable and less prone to errors that can occur when rolling back a full state.

**Cons:**

* Implementation Complexity: Each command must be correctly implemented with its own undo and redo logic. This increases the complexity of each command's implementation and requires careful design to ensure that each command properly and completely reverses its effects.

* Testing Overhead: The need for thorough testing increases as each command has its own undo/redo logic. There's a higher risk of bugs in individual commands, especially in scenarios where commands might interact or affect the results of each other.

* Dependency and State Consistency: Commands must be designed not to rely excessively on the specific state of the application that may be altered by other commands. Ensuring consistency and managing dependencies between commands can be challenging.

* Recovery from Errors: If an error occurs during the execution of an undo or redo operation, recovering and ensuring data integrity can be more complex, as the system must handle partial undos or redos gracefully.
### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_

Currently data is being archived via storing it locally as a `.json` file.

In the future, we are looking to create functions to export the data into different file formats such as `.xlsx`, `.pdf` or `.txt` files.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* HR / Executives with access to company contactsw
* needs to update / contact people frequently
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: access and manage multitudes of contacts quickly and easily

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​             | I want to …​                                 | So that I can…​                                                         |
|----------|--------------------|---------------------------------------------|------------------------------------------------------------------------|
| `* * *`  | HR employee        | add new contacts                            |                                                                        |
| `* * *`  | HR employee        | delete contacts                             | remove entries that I no longer need                                   |
| `* * *`  | HR employee        | toggle address book visibility              | be able to hide it for simplicity                                      |
| `* *`    | first time user    | not have a cluttered GUI                    | will not be confused                                                   |
| `* *`    | potential user     | see examples in the database                | examine how it should look like when I start using the app for work    |
| `* *`    | frequent user      | optimize command typing                     | accomplish work efficiently                                            |
| `* *`    | frequent user      | quickly scan the database                   | check for missing details                                              |
| `* *`    | frequent user      | search for specific information             | optimize my work performance                                           |
| `* *`    | potential user     | update contact details                      | keep the information in the book up to date                            |
| `* *`    | HR manager         | search for contacts via their attributes    | can easily find a specific person                                      |
| `* *`    | frequent user      | automatically delete duplicate contacts     | stay organized                                                         |
| `* *`    | HR manager         | sort employee contacts via their attributes | can find employees that fit specific criteria                          |
| `* *`    | employer           | be informed of understaffed departments     | HR can hire accordingly                                                |
| `* *`    | HR manager         | attach tags or notes to contacts            | adding personal reminders or categorizations                           |
<!--
| `* * *`  | new user                                   | see usage instructions       | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | add a new person             |                                                                        |
| `* * *`  | user                                       | delete a person              | remove entries that I no longer need                                   |
| `* * *`  | user                                       | find a person by name        | locate details of persons without having to go through the entire list |
| `* *`    | user                                       | hide private contact details | minimize chance of someone else seeing them by accident                |
| `*`      | user with many persons in the address book | sort persons by name         | locate a person easily                                                 |

*{More to be added}*
-->

### Use cases

(For all use cases below, the **System** is the `Hi:Re` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC1 - Add a contact**

**MSS**

1. User requests to add a new contact and input the contact information
2. Hi:Re adds the person to the database
3. Hi:Re shows a message for the successful addition

   Use case ends.

**Extensions**

* 1a. The input format is wrong and cannot be accepted

    * 1a1. Hi:Re shows an error message

      Use case ends.


**Use case: UC2 - Delete a contact**

**MSS**

1. User requests to delete a specific person in the database and input the details
2. Hi:Re prompts the user with a dialog box to confirm deletion
3. User confirms the deletion
4. Hi:Re deletes the person
5. Hi:Re shows a message for the successful deletion

    Use case ends.

**Extensions**

* 1a. The given details are invalid.

    * 1a1. Hi:Re shows an error message

      Use case ends.
  

* 2a. The user chooses to cancel the deletion.

    * 2a1. Hi:Re shows a message that the deletion is cancelled
  
      Use case ends.


**Use case: UC3 - Toggle display**

**MSS**

1. User requests to toggle the display on or off
2. Hi:Re hides or shows the display, based on whether the display was formerly on or off

   Use case ends.

**Extensions**

* (No extensions)

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 employees without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should take less than 3 seconds for the application to finish processing a command and display the results.
5.  Should work on any 32 bit or 64 bit OS.
6.  Should be usable by any regular employee with little to no technical expertise.
7.  Should have no differences in behaviour or performance between Windows 10 and 11.
8.  A HR employee should not be able to see sensitive information but an executive or someone with higher system authority can.

### Glossary

* **Mainstream OS**: Operating Systems (OS) such as Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **HR**: Human Resource, a department associated with managing employee benefits and recruitment
* **MSS**: Main Success Scenario, the main flow of events in a use case
* **Technical expertise**: Any relevant experience in using a computer and software on it
* **Sensitive information**: Important information relating to a person that may cause privacy concerns such as ID details or bank account details.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Adding a contact

### Deleting a contact

1. Deleting a person while display is off.

   1. Prerequisites: 
      * Clear all contacts using the `clear` command. 
      * Add a few contacts in according to [this section](#adding-a-person).
      * Toggle the display using the `$` command, to turn it off.

   1. Test case: `- /id johndoe61`<br>
      Expected: Contact with the ID johndoe61 will be deleted. Details of the deleted contact shown in the status message. Display remains off. 

   1. Test case: `- /id `<br>
      Expected: No person is deleted. Error details shown in the status message.

### Clearing the addressbook

1. Clearing the addressbook while display is off.

   1. Prerequisites: 
   * Clear all contacts using the `clear` command.
   * Add a few contacts in according to [this section](#adding-a-person).
   * Toggle the display using the `$` command, to turn it off.
   <br><br>

   1. Test case: `clear`<br>
      Expected: Addressbook will be cleared. Success message will be displayed. Display remains off. 

   1. Test case: `clear this` _(where there are additional inputs after `clear`)_<br>
      Expected: Similar to previous.

### Adding tags

1. Invalid commands / inputs

   1. Test case: Non-alphanumeric: `tag+ Human Resource`, `tag+ $@Les`<br>
   Expected: No tags added. Error message will be displayed.
   
   2. "Multiple" tags: `tag+ HR, Finance`, `tag+ sales + marketing`<br>
   Expected: Similar to previous.


2. Adding duplicate inputs

   1. Prerequisites:
   * Add in a new tag, e.g. `tag+ test`
   <br><br>
   
   2. Test case: `tag+ test` _(2nd time)_<br>
   Expected: No tags added. Error message will be displayed.

   3. Test case: `tag+ Test` _("duplicated" tag)_<br>
   Expected: Tag will be added. Success message will be displayed.<br>
   Tags are **case-sensitive**.

### Deleting tags

1. Removing a tag that is in use.

   1. Prerequisites: 
   * Clear all contacts using the `clear` command.
   * Add a few contacts in according to [this section](#adding-a-person).
   <br><br>
   
   2. Test case: `tag- finance`<br>
   Expected: No tag removed. Error message will be displayed.<br>
   Tag is still in use by a contact in the addressbook.

### Register an account

1. Invalid inputs.
   
   1. Invalid username: `register /u 12 /p abc12345`<br>
      Expected: No account created. Error message will be displayed.

   2. Invalid password: `register /u test /p 123`<br>
      Expected: No account created. Error message will be displayed.

2. Invalid command.
   
   1. Missing field: `register /u test`<br>
      Expected: No account created. Error message will be displayed.<br><br>

3. Duplicate username.
   
   Test case:<br><br>
   Execute `register /u test /p abc12345` twice.<br><br>
   Expected: Only the first command will create an account. Error message will be displayed for the second command.

### Login an account

1. Invalid inputs.

    1. Invalid username: `login /u 12 /p abc12345`<br>
       Expected: Login fails. Error message will be displayed.

    2. Invalid password: `login /u test /p 123`<br>
       Expected: Login fails. Error message will be displayed.

2. Invalid command.

    1. Missing field: `login /u test`<br>
       Expected: Login fails. Error message will be displayed.<br><br>

3. Another user already logged in.

   Test case: 
   1. Execute `register /u test /p abc12345`.<br>
   2. Execute `login /u test /p abc12345` twice.<br><br>
   
   Expected: Only the first login command will succeed. Error message will be displayed for the second login command.

### Logout an account

1. The user has not logged in.

   Test case: <br><br>
   Launch Hi:Re and then execute `logout` first.<br><br>
   Expected: Logout fails. Error message will be displayed.

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
