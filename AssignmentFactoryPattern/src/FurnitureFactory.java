import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FurnitureFactory extends HouseFactory
{
	@Override
	public HouseEntity createHouse(String item)
	{
		if(item.equals("Sink"))
		{
			return new Furniture("Sink");
		}
		else if(item.equals("Counter"))
		{
			return new Furniture("Counter");
		}
		else if(item.equals("Bed"))
		{
			return new Furniture("Bed");
		}
		else if(item.equals("Dresser"))
		{
			return new Furniture("Dresser");
		}
		else if(item.equals("Bathtub"))
		{
			return new Furniture("Bathtub");
		}
		else 
		{
			return null;
		}
	}

}
