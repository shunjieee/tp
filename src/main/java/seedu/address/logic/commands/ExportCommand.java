package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATH;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonCsvAddressBookStorage;

/**
 * Exports the address book as a JSON file
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "@";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports the address book to a JSON file, created in the same directory as the program. "
            + "\".csv\" prefix is added automatically and does not need to be typed. "
            + "\nParameters: "
            + PREFIX_PATH + "FILEPATH "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_PATH + "contacts ";

    public static final String MESSAGE_SUCCESS = "New addressbook exported to %1$s";
    public static final String MESSAGE_DUPLICATE_FILE = "The file %1$s already exists in the directory";

    private final Path exportTo;

    /**
     * Creates an ExportCommand
     */
    public ExportCommand(Path filePath) {
        requireNonNull(filePath);
        exportTo = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (FileUtil.isFileExists(exportTo)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_FILE, exportTo.toString()));
        }
        try {
            AddressBookStorage exportAddressBookStorage = new JsonCsvAddressBookStorage(exportTo);
            exportAddressBookStorage.saveAddressBook(model.getAddressBook());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(LogicManager.FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(LogicManager.FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, exportTo.toString()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExportCommand)) {
            return false;
        }

        ExportCommand otherExportCommand = (ExportCommand) other;
        return exportTo.equals(otherExportCommand.exportTo);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("exportTo", exportTo)
            .toString();
    }
}
