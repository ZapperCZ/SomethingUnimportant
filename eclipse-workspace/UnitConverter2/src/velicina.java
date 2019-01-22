import java.util.ArrayList;
import java.util.List;

public class velicina {
String name;
String description;
List <Unit>units;
Unit basic;

public velicina(String name,String description) {
	this.name=name;
	this.description=description;
	units=new ArrayList<Unit>();
}
public void addUnit(Unit myUnit) {
	units.add(myUnit);
}
public void setBasicUnit(Unit basicUnit) {
	basic=basicUnit;
}
}
