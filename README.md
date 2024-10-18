
# Lexical Analyzer

A simple lexical analyzer implemented in Java that converts source code into tokens. This project demonstrates the use of regular expressions for tokenization and provides a clear structure for understanding the components of a lexical analyzer.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Code Structure](#code-structure)
- [How It Works](#how-it-works)
- [License](#license)

## Features

- Tokenizes keywords, identifiers, numbers, operators, and delimiters.
- Prints the resulting tokens in a formatted table.
- Reads source code from a file and processes it efficiently.

## Getting Started

### Prerequisites

To run this project, ensure you have the following installed:

- Java Development Kit (JDK) 8 or higher
- An IDE (e.g., IntelliJ IDEA, Eclipse) or a text editor (e.g., VSCode)

### Cloning the Repository

Clone this repository to your local machine using the following command:

```bash
git clone https://github.com/PocketNugget/Lexical-Analyzer.git
```

### Building the Project

1. Navigate to the project directory:

   ```bash
   cd lexical-analyzer
   ```

2. Compile the Java files:

   ```bash
   javac LexicalAnalyzer.java
   ```

## Usage

To run the lexical analyzer, use the following command in your terminal:

```bash
java LexicalAnalyzer <file_path>
```

Replace `<file_path>` with the path to the source code file you want to analyze. For example:

```bash
java LexicalAnalyzer example.txt
```

## Code Structure

The main components of this project include:

- `LexicalAnalyzer.java`: Contains the main logic for tokenizing the source code.
- `Token.java`: Defines the structure of a token, including its type and value.

## How It Works

1. The `LexicalAnalyzer` class reads the source code from a specified file.
2. It uses regular expressions to identify tokens in the code.
3. The identified tokens are stored in a list and printed in a formatted table.

### Example

Given the following code:

```java
while (x <= 10) {
    x = x + 1;
}
```

The analyzer will produce tokens such as:

- `WHILE`, `LPAREN`, `IDENTIFIER`, `LEQ`, `NUMBER`, `RPAREN`, `LBRACE`, `IDENTIFIER`, `ASSIGN`, `IDENTIFIER`, `PLUS`, `NUMBER`, `SEMICOLON`, `RBRACE`.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.


