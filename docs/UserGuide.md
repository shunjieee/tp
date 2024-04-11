---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# Hi:Re User Guide

---

## Table of Contents

- [Welcome Note](#welcome-note)
- [Product Information](#product-information)
- [Quick Start](#quick-start)
- [Commands](#commands)

  1) [Registering an Account](#registering-an-account-register)
  1) [Login into Account](#login-into-account-login)
  1) [Logout from Account](#logout-from-account-logout)
  1) [Adding a Contact](#adding-a-contact)
  1) [Deleting a Contact](#deleting-a-contact)
  1) [Editing a Contact](#editing-a-contact)
  1) [Toggling the display](#toggling-the-display)
  1) [Finding Contacts by Name](#finding-contacts-by-name)
  1) [Listing all matching information](#listing-all-matching-information-ls)
  1) [Undoing a Command](#undoing-a-command-undo)
  1) [Redoing a Command](#redoing-a-command-redo)
  1) [Exporting a Command](#exporting-data)
  1) [Link to User Guide](#link-to-our-user-guide-help)

- [Built-In Features](#built-in-features)
- [FAQ](#faq)
- [Known Issues](#known-issues)
- [Command Summary](#command-summary)
- [Future Integrations](#future-integrations)
- [Support and Feedback](#support-and-feedback)

---

## Welcome Note
Greetings HR employees, managers and executives! 

- Tired from **scrolling** through endless rows in **spreadsheet** applications?
- Worried about **data leak** issues caused by the lack in security of such applications?
- Frustrated by the **slow input** of cell by cell in such applications?
- Need an economically viable HR application?

*Well, Hi:Re, our one-stop employee profiling application is for you!*

We recommend reading our **[product information](#product-information)** section first to understand what Hi:Re
can do for you and if it suits your companies' needs! After that, 
dive into the **[quick start](#quick-start)** section, and we hope you 
enjoy using Hi:Re as your company's HR employee profiling solution!

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

## Product Information

Hi:Re is a **desktop app for managing employee details,
optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI).

That means most work is done via *typing* commands on a command line instead of *clicking* with your mouse!
- **Fast types**, Hi:Re will be your **best assistant** to get your contact management tasks done
  **faster** than traditional GUI apps.
- Regardless of your *technical skill* level, our User Guide will **bring you up to speed** and **quickly master**
  Hi:Re, that is already **streamlined for HR purposes**!

<box type="info" seamless> 

**IMPORTANT: In Hi:Re, we adopt a system where contacts are identified by their email id(company email) without the
company email domain.** <br> 

*John is identified by his email id, john123 without the domain, @123company.com*

Hence, we allow duplicate names and handphone numbers.
Contacts are also labelled with one mandatory tag and optional additional tags.
</box>


<u>Breakdown of commands</u>:
1) Register, Login and Logout: Data Management
2) Adding a Contact 
3) Deleting a Contact 
4) Edit a Contact 
5) Toggling the Display 
6) Find Contacts by name 
7) Listing all matching information 
8) Undoing a Command
9) Redoing a Command 
10) Export data into .csv file
11) Link to our User Guide

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

## Quick Start

1. Ensure you have `Java 11` or above installed in your Computer.

1. Download the latest `HiRe.jar` from [here](https://github.com/AY2324S2-CS2103T-T12-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_.

1. Open a command terminal, `cd` into the folder you put the `HiRe.jar` file in, and use the `java -jar HiRe.jar` command to run the application.<br>

   A GUI similar to the below should appear in a few seconds.<br>
   
   <box type="info" seamless>Note how the app contains some sample data.</box>

   <img src="images/ui/startUp.png" width="452.5" height="369.5"><br>

1. Type commands in the `command box` and press `Enter` to execute it, but first...

1. [Register](#registering-an-account-coderegistercode) an account before you are ready to use Hi:Re!

1. [Log into](#login-into-account-codelogincode) your newly registered account with the correct username and password!

1. Refer to the [Commands](#commands) below for details of each command.

1. Refer to the [Built-In Features](#features) below for details of built-in features.


<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

## Commands

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `()` are the parameters to be supplied by the user.<br>
  e.g. for `- /id (id)`, `(id)` is the parameter to be supplied by the user. The user will input a similar command into the command box: `- /id johndoe69`.

* Words in `[...]` are optional and may be repeated.
  e.g. in  `> /id (id) /tag (tag) [/tag (more tags)..]`, the second `/tag` parameter may be excluded, or repeated as many times as one wants

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

### Registering an Account: `register`

Register an account first to have your own address book!<br><br>
Format: `register /u (username) /p (password)`<br><br>
Example: `register /u johndoe /p 123456`<br>

  <box type="important" seamless>

* This command can also be used by clicking in the `Account` section of the menu bar.  <br><br>
* A valid password is between 6 and 20 characters long and does not contain spaces. <br><br>
* A valid username should be alphanumeric and between 4 and 10 characters long. <br><br>
* Usernames are unique and cannot be duplicated.<br><br>
  </box>

**Confirmation of Successful Registration**<br>

If the user registers successfully, a graphical user interface (GUI) indicative of a successful registration will be displayed, as illustrated below.<br>

  <box type="success">
    GUI upon successful register command <br><br>
    <img src="images/ui/register/1.png" width="452.5" ><br>
  </box> 

* **Error Handling Protocols**<br>

    1. Duplicate username Error: Entry of a non-unique username will trigger an error.<br>
       <box type="wrong">
       Duplicate username error <br><br>
       <img src="images/ui/register/2.png" width="452.5" ><br><br>
       </box>
    2. Incomplete Fields Error: Failure to complete all required fields will trigger an error.<br>
       <box type="wrong">
       Incomplete fields error <br><br>
       <img src="images/ui/register/3.png" width="452.5" ><br><br>
       </box>
    3. Invalid Fields Error: Any input field violating the requirements mentioned above will trigger an error.
       <box type="wrong">
       Invalid fields error <br><br>

        * Invalid username

       <img src="images/ui/register/4.png" width="452.5"><br><br>

        * Invalid password

       <img src="images/ui/register/5.png" width="452.5"><br><br>
       </box>

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

### Login into Account: `login`

Login to access your addressbook. <br><br>
Format: `login /u (username) /p (password)`<br><br>
Example: `login /u johndoe /p 123456`<br>

  <box type="important" seamless>

* This command can also be used by clicking in the `Account` section of the menu bar.  <br><br>
* A valid password is between 6 and 20 characters long and does not contain spaces. <br><br>
* A valid username should be alphanumeric and between 4 and 10 characters long. <br><br>
  </box>

**Confirmation of Successful Login**<br>

If the user Logins successfully, a graphical user interface (GUI) indicative of a successful login will be displayed, as illustrated below.<br>

  <box type="success">
    GUI upon successful login command <br><br>
    <img src="images/ui/login/success.png" width="452.5" height="369.5"><br>
  </box> 

* **Error Handling Protocols**<br>

    1. Incomplete Fields Error: Failure to complete all required fields will trigger an error.<br>
       <box type="wrong">
       Incomplete fields error <br><br>
       <img src="images/ui/login/1.png" width="452.5"><br><br>
       </box>
    2. Invalid Fields Error: Any input field violating the requirements mentioned above will trigger an error.<br>
       <box type="wrong">
       Invalid fields error <br><br>

        * Invalid username

       <img src="images/ui/login/2.png" width="452.5"><br><br>

        * Invalid password

       <img src="images/ui/login/3.png" width="452.5"><br><br>
       </box>
    3. Incorrect Username or Password Error: If the username or password is incorrect, an error will be triggered.<br>
       <box type="wrong">
       Incorrect username or password error <br><br>
       <img src="images/ui/login/4.png" width="452.5"><br><br>
       </box>
    4. Already Logged In Error: If the user is already logged in, he cannot log in again.<br>
       <box type="wrong">
       Already logged in error:<br><br>
       <img src="images/ui/login/5.png" width="452.5" ><br><br>
       </box>

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

### Logout from Account: `logout`

After you end accessing your addressbook, log out to protect the data. <br><br>
Format: `logout`<br><br>

**Confirmation of Successful Logout**<br>

If the user Logouts successfully, a graphical user interface (GUI) indicative of a successful logout will be displayed, as illustrated below.<br>

  <box type="success">
    GUI upon successful logout command <br><br>
    <img src="images/ui/logout/success.png" width="452.5" height="369.5"><br>
  </box> 

* **Error Handling Protocols**<br>

    1. Not Logged Error: If the user hasn't logged in, he cannot log out.<br>
       <box type="wrong">
       Not Logged Error:<br><br>
       <img src="images/ui/logout/notloggederror.png" width="452.5" ><br><br>
       </box>

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---
       
### Adding a contact: `+`

  Adds a person to the address book.<br><br>

  Format: <code>+ /name (name) /id (id) /hp (handphone) /tag (tag) [/tag (more tags)...] </code><br>

  Example: <code>+ /name John Doe /id johndoe41 /hp 98765432 /tag Finance</code><br>
        <code>+ /name John Doe /id johndoe41 /hp 98765432 /tag Finance /tag Sales</code><br>

  <box type="important" seamless>

  * The order of input values is interchangeable and doesn't matter. Feel free to input the details in any sequence as long as all required information is provided.

  * The ID must be unique for each contact. ID cannot duplicate with existing IDs. Attempting to use a duplicate ID will result in an error.

  * The ID can include alphabets, numbers, and special characters, but cannot be blank and should not contain whitespaces.

  * The phone number should only contain numbers, and it should be at least 3 digits long.
  * Only one phone number is allowed. Refer to future integrations for more info. 

  * The name should only contain alphanumeric characters and spaces, and should not be blank.

  * All tags for a person **MUST** already exist in a tag list. You can add tags to a tag list with the [add tag](#add-tag-tag) command.

  * A person **MUST** have at least one tag, but can have more than one tag (like in the example)
  
  </box>

  (The Initial UI before addition)                       
    <img src="images/ui/add/beforeAdd.png" width="452.5" height="369.5"><br>

  * **Confirmation of Successful Contact Addition**<br>

  Following the accurate input of the command, a graphical user interface (GUI) indicative of a successful contact addition will be displayed, as illustrated below.
  <box type="success">
    GUI upon successful addition command <br><br>
    <img src="images/ui/add/afterAdd.png" width="452.5" height="369.5"><br><br>
  </box>

  * **Error Handling Protocols**<br> 

    1. Duplicate ID Error: Entry of a non-unique ID will trigger an error. 
       <box type="wrong">
       Duplicate ID error <br><br>
       <img src="images/ui/add/duplicateId.png" width="452.5"><br><br>
       </box>

    2. Incomplete Fields Error: Failure to complete all required fields will trigger an error. 
       <box type="wrong">
       Incomplete fields error <br><br>
       <img src="images/ui/add/insufficientFields.png" width="452.5"><br><br>
       </box>
     
    3. Invalid Fields Error: Any input field violating the requirements mentioned above will trigger an error.
       <box type="wrong">
       Invalid fields error <br><br>
    
       * Invalid name
       
       <img src="images/ui/add/invalidname.png" width="452.5"><br><br>
    
       * Invalid phone number
       
       <img src="images/ui/add/invalidhp.png" width="452.5"><br><br>
       
       * Invalid id
       
       <img src="images/ui/add/invalidid.png" width="452.5"><br><br>
       </box>

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

### Deleting a contact: `-`

  Deletes a person with his specified ID.<br><br>

  Format: <code>- /id (id)</code><br>

  Example: <code>- /id johndoe46</code><br>

  <box type="important" seamless>

* The ID can include alphabets, numbers, and special characters, but cannot be blank and should not contain whitespaces.

  </box>
  (The Initial UI before deletion)
  
  <img src="images/ui/delete/beforeDelete.png" width="452.5"><br>

  * **Confirmation of Contact Deletion**<br>
  
  Following the accurate input of the command, if the person with the input id does exist in the address book, a window will pop up asking for your confirmation to delete.
  Click OK to continue with the deletion. After confirmation, a graphical user interface (GUI) indicative of a successful contact deletion will be displayed, as illustrated below.

  <box type="success">
  
  * Pop-up window for confirmation
  
    <img src="images/ui/delete/prompt.png" width="452.5"><br><br>

  * GUI upon successful addition command <br><br>

    <img src="images/ui/delete/afterDelete.png" width="452.5" height="369.5"><br><br>

  </box>

  * **Error Handling Protocols**<br>
  
    1. ID Not Found Error: If there is no such person with the input id in the list, an error will be triggered.
       <box type="wrong">
       ID not found error <br><br>
       <img src="images/ui/delete/idnotfound.png" width="452.5"><br><br>
       </box>
    
    2. Command Cancelled Error: When the confirmation dialog box pops up, if the user chooses to cancel the operation, it can trigger an error.
       <box type="wrong">
       Command cancelled error <br><br>
       <img src="images/ui/delete/deletecancelled.png" width="452.5"><br><br>
       </box>
    
    3. Incomplete Field Error: Failure to input the required id with the correct prefix will trigger an error.
       <box type="wrong">
       Incomplete field error <br><br>
       <img src="images/ui/delete/insufficientfield.png" width="452.5"><br><br>
       </box>

    4. Invalid Field Error: If the input id violates the requirements, it is invalid and should trigger an error.
        <box type="wrong">
        Invalid field error <br><br>
        <img src="images/ui/delete/invalidid.png" width="452.5"><br><br>
        </box>

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

### Editing a contact: `>`

Edits a person in the address book.<br><br>

Format: <code> > (id) /name (name) /hp (handphone) /tag (tag) [/tag (more tags)...]</code><br>

Example: <code> > johndoe41 /name John Joe /hp 98765432</code><br>

  <box type="important" seamless>

* The ID must be the first thing typed in. Other than that, the order of the other fields are interchangeable and doesn't matter. Feel free to input the details in any sequence. 

* Fields to be edited are optional, but at least 1 field must be given. ID does not count. ID is used to identify the contact.

* The phone number should only contain numbers, and it should be at least 3 digits long.

* The name should only contain alphanumeric characters and spaces, and should not be blank.

* If the fields match the current contact's fields exactly, the edit will still go through and not give a duplicate person error message. This is due to our unique identifier id.

* Should you wish to edit the id of the person, please delete the contact and re-add the contact with the correct id. 

* When editing tags, the existing tags of the person will be removed (i.e adding of tags is not cumulative.)

  </box>

(The Initial UI before editing)                       
<img src="images/ui/edit/beforeEdit.png" width="500" height="250"><br>

* **Confirmation of Successful Contact Editing**<br>

Following the accurate input of the command, a graphical user interface (GUI) indicative of a successful contact addition will be displayed, as illustrated below.
<box type="success">
GUI upon successful addition command <br><br>
<img src="images/ui/edit/afterEdit.png" width="500" height="250"><br><br>
</box>

* **Error Handling Protocols**<br>
    1. No ID Error: No ID given will trigger an error.
       <box type="wrong">
       No ID error <br><br>
       <img src="images/ui/edit/editinvalidId.png" width="452.5"><br><br>
       </box>

    2. Incomplete Fields Error: Failure to complete at least 1 field will trigger an error.
       <box type="wrong">
       Incomplete fields error <br><br>
       <img src="images/ui/edit/editMissingField.png" width="452.5"><br><br>
       </box>

    3. Invalid Fields Error: Any input field violating the requirements mentioned above will trigger an error.
       <box type="wrong">
       Invalid fields error <br><br>

        * Invalid name

       <img src="images/ui/edit/editInvalidName.png" width="452.5"><br><br>

        * Invalid phone number

       <img src="images/ui/edit/editInvalidHp.png" width="452.5"><br><br>

        * Id not found

       <img src="images/ui/edit/editidNotFound.png" width="452.5"><br><br>
       </box>

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

### Toggling the display: `$`

Toggles the display to view / hide the contacts panel of Hi:Re. This allows users to maintain a cleaner and less-cluttered application workspace, especially if there are many contacts.<br><br>

  Format: <code>$</code><br>

  <box type="definition">
    Viewing contacts.<br><br>
    <img src="images/ui/toggle/view.png" width="452.5" height="369.5"><br><br>
  </box>

  <box type="definition" theme="info">
    Hiding contacts.<br><br>
    <img src="images/ui/toggle/hide.png" width="452.5" height="369.5"><br><br>
  </box>

  <box type="important" seamless>

* Note that when the contacts panel is hidden, commands that show a list of contacts (like `ls` or `?`) will consequently not appear to do anything. Thus, if the result of one of these commands is unexpectedly empty, try toggling the panel and re-entering the command again.

  </box>
  
  <div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

### Finding Contacts by Name: `?`

Finds all contacts in the Hi:Re app that has names matching the words entered.

Format: <code> ? (keyword) </code><br>

<box type="important" seamless>

* All names consist of alphanumeric characters, hence any non-alphanumeric characters used will result in no contacts found.
* All names that contain the `(keyword)` entered will be matched.<br>
E.g `? jo` will give John if John exists in the contacts list.
* More than one `(keyword)` can be entered, separated by spaces. All names that match **any** of the words will be listed.<br>
E.g `? jo a` will give John and Ali if both of them exists in the contacts list.
* `(keyword)` used to match names are **CASE-INSENSITIVE**.

  </box>

Example: 

  <box type="definition">
    Finding with one `(keyword)`: <code> ? al </code><br><br>
    <img src="images/ui/find/1.png" width="452.5" height="369.5"><br><br>
  </box>

  <box type="definition" theme="info">
    Finding with more than one `(keyword)`: <code> ? al ch </code><br><br>
    <img src="images/ui/find/2.png" width="452.5" height="369.5"><br><br>
  </box>


<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

### Listing all matching information: `ls`

List has different functionalities.<br><br>

Format: <code>ls (args)</code><br>

<code>(args)</code> = <code>-a</code>: List all contacts in the address book. <br>
<code>(args)</code> = <code>-t</code>: List all tags available. <br>
<code>(args)</code> = <code>(tag_name)</code>: List all contacts with <code>(tag_name)</code>. <br>

<box type="important" seamless>

* All tags consist of alphanumeric characters, hence any non-alphanumeric characters used will result in no contacts found.<br>
* More than one `(tag_name)` can be entered, separated by spaces. All tags that match **any** of the words will be listed.<br>
E.g `ls H fin` will list all contacts with tags containing `H` **OR** `fin`.<br>
* `(tag_name)` used to match tags are **CASE-SENSITIVE**.

  </box>

Example: 

  <box type="definition">
    <code>-a</code>: List all contacts in the address book.<br>
    Command: <code>ls -a</code><br><br>
    <img src="images/ui/list/image1.png" width="452.5" height="369.5"><br><br>
  </box>

  <box type="definition" theme="info">
    <code>(args)</code> = <code>-t</code>: List all tags available.<br>
    Command: <code>ls -t</code><br><br>
    <img src="images/ui/list/image2.png" width="452.5"><br><br>
  </box>

  <box type="definition">
    <code>(tag_name)</code>: List all contacts with ONE <code>(tag_name)</code>.<br>
    Example: <code>ls H</code><br><br>
    <img src="images/ui/list/image3.png" width="452.5"><br><br>
  </box>

  <box type="definition" theme="info">
    <code>(tag_name)</code>: List all contacts with MORE THAN ONE <code>(tag_name)</code>.<br>
    Example: <code>ls H fin</code><br><br>
    <img src="images/ui/list/image4.png" width="452.5"><br><br>
  </box>

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

### Add Tag: `tag+`

Add tag into a tag list.<br><br>

Format: <code>tag+ (tag_name)</code><br>

<box type="important" seamless>

* Default tags: `HR`, `operations`, `finance`, `marketing`, `IT`, `sales` & `RnD`
* Tags are **CASE-SENSITIVE**.
  
  </box>

Example: 

 <box type="success">
    Adding the tag <code>CEO</code>: <code>tag+ CEO</code><br><br>
    <img src="images/ui/tag_plus/1.png" width="452.5"><br><br>
  </box>

**Error Handling Protocols**<br>

  <box type="wrong">
    Duplicate tags. <br><br>
    <img src="images/ui/tag_plus/2.png" width="452.5" ><br><br>
       </box>
  

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

### Delete Tag: `tag-`

  Delete tag from the tag list.<br><br>

  Format: <code>tag- (tag_name)</code><br>

<box type="important" seamless>

* Tag cannot be removed if a person is tagged with the tag-to-be-removed.
  </box>

Example: 

 <box type="success">
    Removing the tag <code>HR</code>: <code>tag- HR</code><br><br>
    <img src="images/ui/tag_minus/1.png" width="452.5"><br><br>
  </box>

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

### Undoing a Command: `undo`

Restores the address book to the state before the previous **undoable** command was executed.<br>
Format: `undo`<br>
<box type="important" seamless>

* This command can also be used by clicking in the `edit` section of the menu bar.  <br><br>
* Undoable commands: those commands that modify the address book’s content (add, delete, edit and clear). 
* All other commands (including adding and deleting tags) **CANNOT** be undone.
  </box>

Example:<br>
1. Delete the person with the id `johndoe41`.<br>
2. Undo the deletion. The person will reappear.<br><br>

* **Confirmation of Successful Undo**<br>

  If there is still any executed command that can be undone, a graphical user interface (GUI) indicative of a successful undo will be displayed, as illustrated below.<br>

  <box type="success">
    GUI upon successful undo command <br><br>
    <img src="images/ui/undo/1.png" width="452.5"><br>
  </box> 

* **Error Handling Protocols**<br>

    1. No Undoable Command Error: If there is no more executed command that can be undone, an error will be triggered.<br>
       <box type="wrong">
       No undoable command error <br><br>
       <img src="images/ui/undo/2.png" width="452.5" ><br><br>
       </box>

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

### Redoing a Command: `redo`

Reverses the most recently undone command.<br>
Format: `redo`<br>

  <box type="important" seamless>

* This command can also be used by clicking in the `edit` section of the menu bar. Typing the command `redo` in the command box will not work. <br>

  </box>
Example:<br>
1. Delete the person with the id `johndoe41`.<br>
2. Undo the deletion. The person will reappear.<br>
3. Redo the undone deletion. The person will be deleted again.<br><br>

* **Confirmation of Successful Redo**<br>

  If there is still any undone command that can be redone, a graphical user interface (GUI) indicative of a successful redo will be displayed, as illustrated below.<br>

  <box type="success">
    GUI upon successful redo command <br><br>
    <img src="images/ui/redo/1.png" width="452.5" ><br>
  </box> 

* **Error Handling Protocols**<br>

    1. No Redoable Command Error: If there is no more undone command that can be redone, an error will be triggered.<br>
       <box type="wrong">
       No redoable command error <br><br>
       <img src="images/ui/redo/2.png" width="452.5" ><br><br>
       </box>

  <div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

### Exporting data: `@`
[back to top](#table-of-contents)

Exports all application data to a comma delimited file in the same directory the app is in.<br>

Format: `@ /filename (filename)`<br>

Example: <code> export /file contacts </code><br>

  <box type="important" seamless>

* Note that you need not add the `.csv` file extension to the filename argument when executing this command - the application will do this for you. Hence, typing `@ /filename contacts` will export the data to `contacts.csv`.
  
* The filename should follow standard filename conventions on whatever system you are using. (e.g. no special characters)

* If a CSV file already exists with the given filename, the command will **not** execute and an error will be thrown.

  </box>

* **Confirmation of Successful Export**<br>

   Following the accurate input of the command, a graphical user interface (GUI) indicative of a successful export will be displayed, as illustrated below.<br>

  <box type="success">
    GUI upon successful export command <br><br>
    <img src="images/ui/export/afterExport.png" width="452.5"><br><br>
  </box> 

* **Error Handling Protocols**<br>
  1. Invalid Filename Error: If the given filename is invalid given for the current system, an error will be triggered.<br>
      <box type="wrong">
      Invalid filename error <br><br>
      <img src="images/ui/export/invalidName.png" width="452.5"><br><br>
      </box>

  1. Filename Already Taken Error: If there already exists a CSV file with the given filename in the same directory as the program, an error will be triggered.<br>
      <box type="wrong">
      Filename already taken error <br><br>
      <img src="images/ui/export/alreadyExists.png" width="452.5"><br><br>
      </box>
  
  <div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

## Built-In Features

Features are built-in for the ease of use. They do not require any commands for it to work.

### Sort
  The addressbook is sorted in alphabetical order every time a new contact is added / deleted.<br><br>

  <box type="definition">
    Before.<br><br>
    <img src="images/ui/sort/beforeSort.png" width="452.5" height="369.5"><br><br>
  </box>

  <box type="definition" theme="info">
    After.<br><br>
    <img src="images/ui/sort/afterSort.png" width="452.5" height="369.5"><br><br>
  </box>

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

### Delete sample data

  Sample data is deleted when you add the first contact into the addressbook.<br><br>
  

  <box type="definition">
    Before.<br><br>
    <img src="images/ui/sampledata/before.png" width="452.5" height="369.5"><br><br>
  </box>

  <box type="definition" theme="info">
    After.<br><br>
    <img src="images/ui/sampledata/after.png" width="452.5" height="369.5"><br><br>
  </box>
  
  <box type="important" seamless>
    It is important that you do **NOT** perform any operations other than adding contacts upon the sample data as there is no guarantee that this feature will work properly afterwards. 
  </box>

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

### Link to our User Guide: `help`

Leads you to our user guide (i.e. this page) for guidance when using the application.

Format: `help`

 <box type="success">
    Pop-up box upon running the command.<br><br>
    <img src="images/ui/help/1.png" width="452.5"><br><br>
  </box>

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

### Information security

  We try our best to protect the private information in your addressbook.<br><br>

  1. **Password Hashing**<br>
     We use the SHA-256 hashing algorithm to hash the passwords.<br>
     Passwords are hashed before being stored in the database.  This means that even if the database is compromised, the passwords are not easily retrievable.<br>

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

## FAQ

<panel header="**Q**: How do I transfer my data to another Computer?">

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Hi:Re home folder.
</panel>

<panel header="**Q**: What is Java 11 and where can I download it?">

**A**: Java 11 is the version of the Java Programming Language that our application uses to function. Any version of Java, 11 and above will work. You can download it from
the [official Java website](https://www.oracle.com/sg/java/).
</panel>

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
1. Sample data sometimes does not clear if edited before a new contact is added. As such, we recommend that users add a new contact immediately upon first logging into Hi:Re before executing any other commands.
1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again. 
***
1. When the contacts panel is hidden by `$`, commands that show a list of contacts (like `ls` or `?`) will consequently not appear to do anything. Thus, if the result of one of these commands is unexpectedly empty, try toggling the panel and re-entering the command again.

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>
  
 ---

## Command summary

Action     | Format
-----------|---------
**Add**    | `+ /name (name) /id (id) /hp (handphone)`
**Delete** | `- /id (id)`
**Edit** | `> (id) /name (name) /hp (handphone)`
**Toggle** | `$`

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

## Future Integrations

**In the future, we plan to integrate some of the following features:**

1. **A more rigorous account management system**, which will allow us to manage users as an admin, and assign different 
    levels of authentication or security.
2. **A remote database management system**, which links all Hi:Re instances on different machines to the same
    Hi:Re database, which will allow for collaboration and other benefits.
3. **More open-ended tagging and field options**, allowing for Hi:Re to be fully customisable as per the
    organisation's requirements.
4. **Multiple phone numbers**, allowing for contacts to add in both their office and personal phone numbers.

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>

---

## Support and Feedback

We are a dedicated team of developers committed to evolving Hi:Re to always be better.

Should you have any enquiries or feedback, do reach out to us at our [website!](https://github.com/AY2324S2-CS2103T-T12-3)

<div style="text-align: right;">
  <a href=#table-of-contents>
    back to top
    </a>
  </div>
