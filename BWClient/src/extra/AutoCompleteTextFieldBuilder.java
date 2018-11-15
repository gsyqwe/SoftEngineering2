package extra;

import java.util.List;

import mainframeui.TField;


public class AutoCompleteTextFieldBuilder
{
	
	public static final AutoCompleteTextField build(TField textField, List<String> cacheData)
	{
		return new AutoCompleteTextField(textField, cacheData);
	}
	
	public static final AutoCompleteTextField build(TField textField)
	{
		return new AutoCompleteTextField(textField);
	}

}
