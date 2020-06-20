package javabasics;

/**
 * Created by Kaiser on 4/4/20.
 */
public class Javabasics {
    public static void main(String [] args) {
        System.out.println("----------Java Basics------------");

        /****
         * Java Virtual Machine:
         * Java was designed for developers to make applications that could run on any platform that supported Java without having to be rewritten or recompiled by the programmer on each separate platform. The JVM makes this possible, because it is aware of the specific instruction lengths and other particularities on the low level of each platform.
         * In order for the Java Virtual Machine to work, it has to be implemented on a given platform according to the Java Virtual Machine specification. The JVM specification defines an abstract rather than a real machine or processor, so that it can be mapped to any platform. This specification describes a set of instructions, a set of registers, a stack, a garbage heap, and a method area. Once the specification in the JVM have been implemented for a given platform, any Java program, which, after compilation, is called bytecode, can run on that platform.
         *
         * The Java Virtual Machine provides a platform-independent way of executing code by abstracting the differences between operating systems and the CPU architectures. With this in mind, no JVM implementation is the same and it tends not to have major effects on your programs, because the power of Java comes from its OS and architecture abstraction.
         *
         * In short, the Java Virtual Machine is what allows Java to be run on platforms without being recompiled or rewritten. The JVM lives inside of the JRE, the Java Runtime Environment, which we'll learn about in the next video.
         *
         */


        /***
         * What is the JDK and JRE?
         * The Java Runtime Environment and the Java Development Kit have tools for running and creating Java programs.
         *
         * The JRE, short for Java Runtime Environment, contains the Java Virtual Machine that we talked about in the last video, as well as browser plug-ins for executing Java applets.
         *
         * The JDK, short for Java Development Kit, is a fully-featured software development kit for Java, including everything from the JRE, as well as compilers, debuggers, and tools to create programs.
         *
         * In other words, the JRE is what you need to run Java programs and the JDK is what you need to develop Java programs. In order to use many integrated development environments or IDEs, you must have the Java Development Kit already installed, because the IDE will use this to compile and debug your program.

         */

        /**
         * Access modifier:
         *
         *          Class       Package         Subclass (Same pkg)     Subclass (Diff pkg)        World
         * Public -    x            x                   x                       x                   x
         * Protected - x            x                   x                       x
         * No modifier-x            x                   x
         * Private-    x
         */
    }
}
