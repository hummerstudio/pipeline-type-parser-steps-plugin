# Pipeline Type Parser Steps Plugin

This plugin provides several steps to parse a variable to the type you really want.

# Implemented Steps

- `toInteger`

    Parse String to Integer.
    
    **Fields:**
        
    - string: The string to parse.
        
    **Example:**
    ```groovy
    env.NUMBER = 2  // env variables are String type
    def number = toInteger string: env.NUMBER
    println(number + 1) // now it's ok to add another number
    ```

- `toFloat`

    Parse String to Float.
    
    **Fields:**
    
    - string: The string to parse.
    
    **Example:**
    ```groovy
    env.NUMBER = 2.1  // env variables are String type
    def number = toFloat string: env.NUMBER
    println(number + 1) // now it's ok to add another number
    ```

- `toDouble`

    Parse string to Double.

    **Fields:**
    
    - string: The string to parse.
    
    **Example:**
    ```groovy
    env.NUMBER = 2.1  // env variables are String type
    def number = toDouble string: env.NUMBER
    println(number + 1) // now it's ok to add another number
    ```
    
- `toJson`

    Parse Map to String.
    
    **Fields:**
    
    - map: The object to parse. Can either be a JSON instance or Groovy Map/List instance. Both are supported.
    - pretty(optional): Prettify the output with this number of spaces added to each level of indentation.
    
    **Example:**
    ```groovy
    def example_map = [a:'a_value', b:'b_value']
    def json = toJson map: example_map
    // or pretty print it, indented with a configurable number of spaces
    def json = toJson map: example_map, pretty: 4
    //Do some manipulation
    println(json)
    ```