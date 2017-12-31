import entity.AutherEntity;
import entity.BookEntity;
import service.BookService;
import utils.LibrabryConsistBook;

import java.io.IOException;
import java.util.Scanner;

public class Manipulator {
    private  BookService bookService;

    public Manipulator() {
        this.bookService=new BookService();
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        String inputLine;
        while(true){
            inputLine=scanner.nextLine();
            if(inputLine.toLowerCase().equals("bye library") || inputLine.toLowerCase().equals("exit"))
                return;
            if(inputLine.toLowerCase().equals("all books"))
                bookService.getAllBook();
            parsingLine(inputLine);
        }

    }

    private void parsingLine(String inputLine) {
        String command=inputLine.split(" ")[0].trim();
        switch (command){
            case "add": parseAdd(inputLine, command); break;
            case "remove": parseRemove(inputLine, command); break;
        }
        /*if(command.equals("add")) {
            parseAdd(inputLine, command);
        }*/


        /*String auther=parseAutherTitle(inputLine, command, bookTitle);
        System.out.println("Автор "+auther);
        System.out.println("Название "+bookTitle);
        System.out.println("Команда "+command);*/
    }

    private void parseRemove(String inputLine, String command) {
        String title = inputLine.substring(command.length()+1);
        bookService.remove(title);
    }

    private void parseAdd(String inputLine, String command) {
        String title=parseBookTitle(inputLine);
        String auther=parseAutherTitle(inputLine, command, title);
        try {
            bookService.addBook(title, auther);
        } catch (LibrabryConsistBook librabryConsistBook) {
            librabryConsistBook.printStackTrace();
            System.out.println(librabryConsistBook.getMessage());
        }
    }

    private String parseAutherTitle(String line, String command, String bookTitle) {
        line=line.substring(command.length()+1);
        StringBuilder temp=new StringBuilder(line);
        return new String(new StringBuffer(temp.reverse().substring(bookTitle.length()+2)).reverse()).trim();
    }

    private String parseBookTitle(String inputLine) {
        int sIndex=inputLine.indexOf('"');
        int fIndex=inputLine.indexOf('"', sIndex+1);
        return inputLine.substring(sIndex+1, fIndex);
    }
}
