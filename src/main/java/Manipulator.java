import service.BookService;
import service.interfacesService.BookServiceInter;
import utils.InvalidData;
import utils.LibrabryConsistBook;
import utils.LibrabryHavnotBook;

import java.util.Scanner;

public class Manipulator {
    private BookServiceInter bookService;

    public Manipulator() {
        this.bookService = new BookService();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String inputLine;
        while (true) {
            inputLine = scanner.nextLine();
            if (inputLine.toLowerCase().equals("bye library") || inputLine.toLowerCase().equals("exit"))
                return;
            if (inputLine.toLowerCase().equals("all books"))
                bookService.getAllBook();
            else
            if((inputLine.split(" ")[0]+" "+inputLine.split(" ")[1]).equals("edit book"))
                parseEdit(inputLine);
            else
            parsingLine(inputLine);
        }
    }

    private void parsingLine(String inputLine) {
        String command = inputLine.split(" ")[0].trim();
        switch (command.toLowerCase()) {
            case "add":
                parseAdd(inputLine, command);
                break;
            case "remove":
                parseRemove(inputLine, command);
                break;
            default:
                System.out.println("The command "+command+" not recognized");
                break;
        }
    }

    private void parseEdit(String inputLine) {
        try {
            String title = parseBookTitle(inputLine);
            bookService.editBook(title);
        } catch (LibrabryHavnotBook | InvalidData librabryHavnotBook) {
            System.out.println(librabryHavnotBook.getMessage());
        }
    }

    private void parseRemove(String inputLine, String command) {
        String title = null;
        try {
            title = parseBookTitle(inputLine);
            bookService.remove(title);
        } catch (InvalidData invalidData) {
            invalidData.printStackTrace();
        }
    }

    private void parseAdd(String inputLine, String command) {
        try {
            String title = parseBookTitle(inputLine);
            String auther = parseAutherTitle(inputLine, command, title);
            bookService.addBook(title, auther);
        } catch (LibrabryConsistBook | InvalidData librabryConsistBook) {
            System.out.println(librabryConsistBook.getMessage());
        }
    }

    private String parseAutherTitle(String line, String command, String bookTitle) {
        line = line.substring(command.length() + 1);
        StringBuilder temp = new StringBuilder(line);
        return new String(new StringBuffer(temp.reverse().substring(bookTitle.length() + 2)).reverse()).trim();
    }

    private String parseBookTitle(String inputLine) throws InvalidData {
        int sIndex = inputLine.indexOf('"');
        int fIndex = inputLine.indexOf('"', sIndex + 1);
        if (sIndex == -1 || fIndex == -1)
            throw new InvalidData("Invalid data error");
        return inputLine.substring(sIndex + 1, fIndex);
    }
}
