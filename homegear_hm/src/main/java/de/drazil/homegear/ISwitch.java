package de.drazil.homegear;

public interface ISwitch {
	public void setState(Boolean state) throws Exception;

	public Boolean getState() throws Exception;
}
