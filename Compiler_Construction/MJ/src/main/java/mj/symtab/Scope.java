package mj.symtab;

import java.util.LinkedList;
import java.util.List;

public class Scope {
	public Scope parent;		// reference to parent scope
	public List<SymObject> locals;	// local variables of this scope

	public Scope() {
		locals = new LinkedList<SymObject>();
	}
}