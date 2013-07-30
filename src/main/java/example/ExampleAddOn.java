package example;

import smaf.*;
import net.xeoh.plugins.base.annotations.PluginImplementation;

@PluginImplementation
public class ExampleAddOn implements SMAF_GenericAddOn {

	public ExampleAddOn() {
		System.out.println("FOO!!");
		
	}
	
	public String GetAddOnName(){
		return "Example Addon";
	}
}
