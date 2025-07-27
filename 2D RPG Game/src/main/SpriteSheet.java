package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	public BufferedImage imgSheet;
	int widthSheet;
	int heightSheet;
	public int maxNumber;
	int	x, y;
	int sizeWidth;
	int sizeHeight;
	int step;
	public BufferedImage animation[];
	
	public SpriteSheet(String path) {
		try {
			imgSheet = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	* <h1>constructor SpriteSheet</h1>
	*
	* <p>public SpriteSheet(String path, int widthSheet, int heightSheet, int maxNumber, int x, int y,
			int sizeWidth, int sizeHeight)</p>
	* <p>
	* <b>Chú thích</b> Có thể lấy thông số ở link sau:
	* <a href="https://ezgif.com/crop">https://ezgif.com/crop</a> <br>
	* @param path là String đường dẫn đến file ảnh SpriteSheet
	* @param widthSheet là chiều dài của ảnh SpriteSheet
	* @param heightSheet là chiều cao của ảnh SpriteSheet
	* @param maxNumber là số hình ảnh, số Sprite trong một SpriteSheet
	* @param x là tọa độ trục hoành của góc trên bên trái sprite đầu tiên
	* @param y là tọa độ trục tung của góc trên bên trái sprite đầu tiên
	* @param sizeWidth là chiều dài của một sprite
	* @param sizeHeight là chiều cao của một sprite
	*   
	* @author  Nguyễn Bình Minh
	* @version 1.0
	* @since   2025-07-04
	*/
	public SpriteSheet(String path, int widthSheet, int heightSheet, int maxNumber, int x, int y,
			int sizeWidth, int sizeHeight) {
		super();
		try {
			imgSheet = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.widthSheet = widthSheet;
		this.heightSheet = heightSheet;
		this.maxNumber = maxNumber;
		this.x = x;
		this.y = y;
		this.sizeWidth = sizeWidth;
		this.sizeHeight = sizeHeight;
		step = widthSheet/maxNumber;
		this.getSprite();
	}


	/**
	* <h1>getSpriteNum</h1>
	* public BufferedImage getSpriteNum(int index)
	* Hàm này dùng để trả về hình ảnh thứ index trong SpriteSheet
	* Index bắt đầu từ số 0
	* <p>
	* <b>Ví dụ</b> Muốn trích xuất hình ảnh đầu tiên trong chuỗi <b>SpriteSheet</b> <i>player</i> 
	* Ta viết như sau: <i>player</i>.getSpriteNum(0);
	*
	*@param index Đây là tham số để trích xuất hình ảnh như mô tả
	* @author  Nguyễn Bình Minh
	* @version 1.0
	* @since   2025-07-04
	*/
	public BufferedImage getSpriteNum(int index) {
		BufferedImage sprite;
		if(this.imgSheet == null) {
			return null;
		}
		sprite = this.imgSheet.getSubimage(x + index*this.step, y, sizeWidth, sizeHeight);
		return sprite;
	}
	/**
	* <h1>matchSprite</h1>
	* public void getSprite()
	* <p> Hàm này dùng để đưa các Sprite đã cắt từ một SpriteSheet
	* Rồi đưa đưa vào một mảng BufferedImage animation[]
	* Và trả về animation
	* 
	* </p>
	* <p>
	* 
	*
	* @return animation là đối tượng tạo từ this SpriteSheet
	* @author  Nguyễn Bình Minh
	* @version 1.0
	* @since   2025-07-04
	*/
	public void getSprite() {
		BufferedImage animation[];
		animation = new BufferedImage[this.maxNumber];
		for(int i = 0; i < this.maxNumber; i++) {
			animation[i] = this.getSpriteNum(i); 
		}
		this.animation = animation;
	}
}
