package mj.symtab;

import java.util.LinkedList;
import java.util.List;

/**
 Every named object in a program is stored in an SymObject node.
 Every scope has a list of objects declared in this scope.
 */
public class SymObject {
	public static final int
			KIND_NONE = 0,
			KIND_CON = 1,
			KIND_VAR = 2,
			KIND_TYPE = 3,
			KIND_METHOD = 4,
			KIND_PROGRAM = 5;

	public int    kind;		// Constant, Variable, Type, Method, Program
	public String name;		// object name
	public Struct type;	 	// object type
	public int    value;      // Constant: value
	public int level; // Level at which the variable is declared
	public int address;  // Variable, Method: address
	public List<SymObject> locals;
	public int parameters; //Methods: number of parameters

	public SymObject() {
		locals = new LinkedList<SymObject>();
	}

	public SymObject(int kind, Struct type) {
		this();
		this.kind = kind;
		this.type = type;
	}

	public SymObject(int kind, Struct type, String name) {
		this(kind, type);
		this.name = name;
	}

	public SymObject(Struct type, String name, int value) {
		this(KIND_CON, type, name);
		this.value = value;
	}
}