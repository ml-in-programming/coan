# coan
Tool for extraction of stylometric features from Java projects based on [JavaParser](https://github.com/javaparser/javaparser "JavaParser GitHub").

Results can be saved in .csv format.

#### Features that can be collected:
* Visual representation of ASTs of methods in xml-format;
* Methods, number of:
    * methods, characters, lines, parameters, lambdas
* Variables, number of:
    * local variables
    * fields
    * private fields
    * public fields
    * length of fields' names
    * length of variables' names
* Number of classes, interfaces, inner-classes
* Number of keywords:
    * for, while, if, else, else-if, ternary operators, break, continue, null
* Number of constants:
    * int, String, char
* Number of comments:
    * JavaDoc, line comments, block comments
* Layout, number of:
    * Lines, empty lines, tabs, spaces, total length of file
* Layout features:
    * Punctuation before '{' brace
    * Tabs or spaces in the beginning of lines
* Ast-based:
    * Maximal depth
    * Average depth of vertices by type
    * TF of vertices by type

     
