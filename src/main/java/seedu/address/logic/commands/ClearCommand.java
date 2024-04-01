package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    private ReadOnlyAddressBook addressBookBoforeClear;

    /**
     * Retrieves the original {@code AddressBook} instance that is targeted to be replaced by an empty one.
     *
     * @return The {@code AddressBook} instance that was initially marked to be cleared.
     */
    public ReadOnlyAddressBook getAddressBookBeforeClear() {
        return addressBookBoforeClear;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        addressBookBoforeClear = model.getAddressBook();
        model.setAddressBook(new AddressBook());
        model.addExecutedCommand(this);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
