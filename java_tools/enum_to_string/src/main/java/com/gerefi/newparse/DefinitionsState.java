package com.gerefi.newparse;

import com.gerefi.VariableRegistry;
import com.gerefi.newparse.parsing.Definition;

public interface DefinitionsState {
    void addDefinition(VariableRegistry variableRegistry, String name, String value, Definition.OverwritePolicy overwritePolicy);

}
