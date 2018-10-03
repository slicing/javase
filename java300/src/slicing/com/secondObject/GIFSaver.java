package slicing.com.secondObject;

public class GIFSaver implements ImageSaver {


	@Override
	public void save() {
		System.out.println("将图片保存为Gif格式");
	}
}
class JPEGSaver implements ImageSaver{

	@Override
	public void save() {
		System.out.println("将图片保存为JPEG格式");
	}
}
class PNGSaver implements ImageSaver{

	@Override
	public void save() {
		System.out.println("将图片保存为png格式");
	}
}
class TypeChooser{
	public static ImageSaver getSaver(String type){
		if(type.equalsIgnoreCase("GIF")){
			return new GIFSaver();
		}else if(type.equalsIgnoreCase("JPEG")){
			return new JPEGSaver();
		}else if(type.equalsIgnoreCase("PNG")){
			return new PNGSaver();
		}else {
			return null;
		}
	}
}
