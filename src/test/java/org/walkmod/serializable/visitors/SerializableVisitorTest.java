/*
 Copyright (C) 2015 Raquel Pau.
 
Walkmod is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Walkmod is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with Walkmod.  If not, see <http://www.gnu.org/licenses/>.*/
package org.walkmod.serializable.visitors;

import org.junit.Assert;
import org.junit.Test;
import org.walkmod.javalang.ast.CompilationUnit;
import org.walkmod.javalang.ast.body.ClassOrInterfaceDeclaration;
import org.walkmod.javalang.test.SemanticTest;

public class SerializableVisitorTest extends SemanticTest {

	@Test
	public void simpleTest() throws Exception{
		CompilationUnit cu = compile("public class Foo { private void bar(){} }");
		cu.accept(new SerializableVisitor<Object>(), null);
		ClassOrInterfaceDeclaration declaration = (ClassOrInterfaceDeclaration) cu.getTypes().get(0);
		Assert.assertNotNull(declaration.getImplements());
	}
	
	@Test
	public void testWhenIsSerializableSubclass() throws Exception{
		CompilationUnit cu = compile("public class Foo implements java.io.Serializable{}");
		cu.accept(new SerializableVisitor<Object>(), null);
		ClassOrInterfaceDeclaration declaration = (ClassOrInterfaceDeclaration) cu.getTypes().get(0);
		Assert.assertEquals(1, declaration.getImplements().size());
	}
}
