

import java.io.Serializable;

public class ItemProduct implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int itemProductId;
	private String name;
	private float price;
	private boolean errorFree;
	private String error;
	
	public void setItemProductId(int ProductId)
	{
		itemProductId = ProductId;
	}
	
	public int getItemProductID()
	{
		return itemProductId;
	}
	
	public void setName(String itemName)
	{
		name = itemName;
	}

	public String getName()
	{
		return name;
	}
	
	public void setIPrice(float itemPrice)
	{
		price = itemPrice;
	}
	
	public float getPrice()
	{
		return price;
	}
	
	public void setErrorTag(boolean error)
	{
		errorFree = error;
	}
	
	public boolean getErrorTag()
	{
		return errorFree;
	}
	
	public void setErrorMsg(String msg)
	{
		error = msg;
	}
	
	public String getErrorMsg()
	{
		return error;
	}
	
}
