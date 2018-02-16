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

/**
 * 
 * This is the main application.  Note that while it is a JavaFX application it doesn't
 * actually "show" the main scene.  We just need the application for the fileChooser.
 */
public class HouseBuilder extends Application{
	
	//HouseArea house;
	/**
	 * Manually construct a house
	 */
	//List<HouseEntity> structures = new ArrayList<HouseEntity>();
	HouseEntity house;
	HouseEntity block;
	HouseEntity structure;
	HouseEntity level;
	public void buildHouse()
	{
		HouseFactory areaFactory = new HouseAreaFactory();
		FurnitureFactory furnitureFactory = new FurnitureFactory();
		
		house = areaFactory.buildHouse("House");
		level = areaFactory.buildHouse("Downstairs");
		structure = areaFactory.buildHouse("Kitchen");
		
		block = furnitureFactory.buildHouse("Sink");
		structure.add(block);
		block = furnitureFactory.buildHouse("Counter");
		structure.add(block);
		
		level.add(structure);
		
		house.add(level);
		
		
		Furniture block1 = new Furniture("Sink");
        Furniture block2 = new Furniture("Counter");
        Furniture block3 = new Furniture("Bed");
        Furniture block4 = new Furniture("Dresser");
        Furniture block5 = new Furniture("Bathtub");

        //Initialize composite structures
        HouseArea structure = new HouseArea("Kitchen");
        HouseArea structure1 = new HouseArea("Bedroom");
        HouseArea structure2 = new HouseArea("Bathroom");
        HouseArea structure3 = new HouseArea("Downstairs");
        HouseArea structure4 = new HouseArea("Upstairs");
        house = new HouseArea("House");
        
        //Build the house
        house.add(structure3);
        house.add(structure4);
        
        structure4.add(structure1);
        structure4.add(structure2);
        
        structure3.add(structure);
      
        structure.add(block1);
        structure.add(block2);
        structure1.add(block3);
        structure1.add(block4);
        structure2.add(block5);     
	}
	
	/**
	 * Save using serialization
	 * @param fileName
	 */
	public void save(String fileName){
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream( new FileOutputStream(fileName));
			oos.writeObject(house);  //serializing employee
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	
	public void countHouseContents(){
		System.out.println("House includes: " + house.countContents() + " areas and/or furniture items.");
	}
	
	public void printHouseSpecs(){
		house.listHouseSpecs(0);
	}
	
	public HouseEntity getHouse(){
		return house;
	}
	
	
	/**
	 * Restore from serialized form
	 * @param fileName
	 */
	public void restore(String fileName)
	{
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream( new FileInputStream(fileName));
			house = (HouseArea) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	public String getFileName(Stage primaryStage){
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		     // System.out.println("Current relative path is: " + s); 
		FileChooser fileChooser = new FileChooser();
		 fileChooser.setInitialDirectory(new File("C:/Users/gauravsinghal/Desktop/"));  // This is optional
		 fileChooser.setTitle("Serialization File");
		// /Users/nanettegormley/Desktop/temp
	     // System.out.println("Current relative path is: " + s);
		 File file = fileChooser.showOpenDialog(primaryStage);
		 return file.getAbsolutePath();
	}
	
	 public static void main(String[] args) {
		 launch(args);      
	 }

	@Override
	public void start(Stage primaryStage) 
	{
		try
		{
			  HouseBuilder houseBuilder = new HouseBuilder();
		      houseBuilder.buildHouse();
		      Path currentRelativePath = Paths.get("");
		      String s = currentRelativePath.toAbsolutePath().toString();
		     // System.out.println("Current relative path is: " + s);
		      houseBuilder.save("C:/Users/gauravsinghal/Desktop/myHouse.ser");
		      String filename = houseBuilder.getFileName(primaryStage);
		      houseBuilder.restore(filename);
		      houseBuilder.printHouseSpecs();
		      houseBuilder.countHouseContents();		
		}
		catch (NullPointerException ignore)
		{
			
		}
		finally
		{
			
		}

	}      	       
}

