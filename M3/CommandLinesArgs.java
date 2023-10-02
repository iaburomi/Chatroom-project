package M3;

public class CommandLinesArgs {
    public static void main(String[] args) {
		System.out.println("Commonly args are used to pass configuration options to the program");
		for(int i = 0; i < args.length; i++) {
			System.out.println("args[" + i + "] = " + args[i]);
		}
    }
}