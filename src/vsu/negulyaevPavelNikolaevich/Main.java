package vsu.negulyaevPavelNikolaevich;
import util.ArrayUtils;
import util.SwingUtils;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Storage storage = new Storage();
        SolutionClass Solution = new SolutionClass();
        if(needCmd()) runCmd(storage, Solution);
        if(needWindow()) winMain();
    }
    private static void runCmd(Storage storage, SolutionClass logic) throws IOException {
        CmdCommands cmdCommand = CmdCommands.STATIC;
        Scanner scanner = new Scanner(System.in);

        String command;
        String inputFile;
        String outputFile = "-";
        List<Integer> list1 = Arrays.asList(3, 4, 9);
        List<Integer> list2 = Arrays.asList(2, 5, 8);
        Storage tempStorage = new Storage();

        int[][] arr;
        logic.createNewList(list1, list2, storage);
        arr = storage.listsInMatrix;

        System.out.println("Commands:");
        System.out.println("-run     - run program");
        System.out.println("-help    - show commands");
        System.out.println("-exit    - close program");
        System.out.println("-read    - read array from console");
        System.out.println("-input   - enter way to input file");
        System.out.println("-output  - enter way to output file");

        while (cmdCommand != CmdCommands.EXIT) {
            command = scanner.next();
            if (Objects.equals(command, "-run")) {
                cmdCommand = CmdCommands.RUN;
            } else if (Objects.equals(command, "-help")) {
                cmdCommand = CmdCommands.HELP;
            } else if (Objects.equals(command, "-exit")) {
                cmdCommand = CmdCommands.EXIT;
            } else if (Objects.equals(command, "-read")) {
                cmdCommand = CmdCommands.READ_FROM_CONSOLE;
            } else if (Objects.equals(command, "-input")) {
                cmdCommand = CmdCommands.ENTER_INPUT_FILE;
            } else if (Objects.equals(command, "-output")) {
                cmdCommand = CmdCommands.ENTER_OUTPUT_FILE;
            } else {
                System.out.println("Error, this command is not found. Try again: ");
            }

            switch (cmdCommand) {
                case EXIT -> System.exit(1);
                case RUN -> {
                    logic.createNewList(list1, list2, storage);
                    if (!Objects.equals(outputFile, "-")) {
                        ArrayUtils.writeArrayToFile(outputFile, storage.sortedArray);
                    }
                    SolutionClass.writeIntArrayToConsole(storage.listsInMatrix);
                    System.out.println("====================================");
                    SolutionClass.printCollection(storage.list);
                }
                case READ_FROM_CONSOLE -> {
                    arr = ArrayUtils.readIntArray2FromConsole();
                    SolutionClass.matrixToLists(arr, tempStorage);
                    list1 = tempStorage.list1;
                    list2 = tempStorage.list2;
                    System.out.println("Matrix updated");
                }
                case ENTER_INPUT_FILE -> {
                    System.out.print("Enter way to input file: ");
                    inputFile = scanner.next();
                    arr = SolutionClass.readIntArray2FromFile(inputFile);
                    SolutionClass.matrixToLists(arr, tempStorage);
                    list1 = tempStorage.list1;
                    list2 = tempStorage.list2;
                    System.out.println("Matrix updated.");

                }
                case ENTER_OUTPUT_FILE -> {
                    System.out.print("Enter way to output file: ");
                    outputFile = scanner.next();
                }
                case HELP -> {
                    System.out.println("Commands:");
                    System.out.println("-run     - run program");
                    System.out.println("-help    - show commands");
                    System.out.println("-exit    - close program");
                    System.out.println("-read    - read array from console");
                    System.out.println("-input   - enter way to input file");
                    System.out.println("-output  - enter way to output file");
                }
            }

        }
    }
    public static void winMain() {
        //SwingUtils.setLookAndFeelByName("Windows");
        Locale.setDefault(Locale.ROOT);
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }
    private static boolean needCmd() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to use cmd? Enter true/false: ");
        return scanner.nextBoolean();
    }

    private static boolean needWindow() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to use UI? Enter true/false: ");
        return scanner.nextBoolean();
    }
}
