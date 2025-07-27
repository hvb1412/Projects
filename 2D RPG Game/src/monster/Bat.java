package monster;

import main.GamePanel;
import main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;

public class Bat extends Monster {

    private Map<String, BufferedImage[]> animations = new HashMap<>();
    private String currentState = "idle"; // Trạng thái mặc định là "idle"
    private int frameIndex = 0;
    private int frameCounter = 0;
    private final int FRAME_SPEED = 10; // Tốc độ animation

    public Bat(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Bat";
        speed = 2;
        maxHp = 1000;
        hp = maxHp;
        attack = 2;
        direction = "left"; // Hướng di chuyển mặc định
        loadAnimations(); // Tải các animation khi khởi tạo
    }

    private void loadAnimations() {
        loadAnimation("attack1", "/monster/Bat-Attack1.png", 8); // 8 frame cho animation "attack1"
        loadAnimation("attack2", "/monster/Bat-Attack2.png", 12);
        loadAnimation("die", "/monster/Bat-Die.png", 10);
        loadAnimation("idle", "/monster/Bat-IdleFly.png", 9);
        loadAnimation("run", "/monster/Bat-Run.png", 8);
        loadAnimation("sleep", "/monster/Bat-Sleep.png", 3);
        loadAnimation("wakeup", "/monster/Bat-WakeUp.png", 15);
    }

    private void loadAnimation(String state, String path, int frameCount) {
        try {
             InputStream is = getClass().getResourceAsStream(path); // Tải ảnh từ đường dẫn
            if (is == null) {
                System.err.println("Không tìm thấy ảnh: " + path);
                return;
            }
            BufferedImage spriteSheet = ImageIO.read(is);
            int frameWidth = spriteSheet.getWidth() / frameCount;
            int frameHeight = spriteSheet.getHeight();

            BufferedImage[] frames = new BufferedImage[frameCount];
            for (int i = 0; i < frameCount; i++) {
                BufferedImage subimage = spriteSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
                System.out.println("Frame " + i + ": width = " + subimage.getWidth() + ", height = " + subimage.getHeight());
                frames[i] = setup(subimage, 9*gp.tileSize/4, 9*gp.tileSize/4);
            }
            animations.put(state, frames); // Lưu animation vào map
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BufferedImage setup(BufferedImage image, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		image = uTool.scaleImage(image, width, height);
		return image;
	}

    @Override
    public void update() {
        super.update(); // Cập nhật các thuộc tính từ lớp cha
        // Cập nhật trạng thái dựa trên HP
        if (hp <= 0) {
            currentState = "die"; // Nếu chết, chuyển sang trạng thái "die"
        } else {
            currentState = "run"; // Nếu còn sống, chuyển sang trạng thái "run"
        }

        // Cập nhật animation frame
        frameCounter++;
//        System.out.println("Bat: " + frameCounter);
        if (frameCounter >= FRAME_SPEED) {
            frameCounter = 0;
            BufferedImage[] frames = animations.get(currentState);
            if (frames != null && frameIndex < frames.length) {
                frameIndex = (frameIndex + 1) % frames.length; // Duyệt qua các frame animation
            }
        }
    }

    @Override
    public void setAction() {
        // Di chuyển ngẫu nhiên
    	actionLockCounter++;

        if (actionLockCounter == 120) {
        	Random rand = new Random();
            String[] directions = {"up", "down", "left", "right"};
            direction = directions[rand.nextInt(directions.length)]; // Lấy hướng ngẫu nhiên
            System.out.println("Direction: " + direction); // In ra để kiểm tra
            actionLockCounter = 0;
        }
        
    }

    @Override
    public void draw(Graphics2D g2) {
        // Vẽ animation của Bat
        BufferedImage[] frames = animations.get(currentState);
//        System.out.println("lengthhhhhhhhhhhhhhh " + frames.length);
        if (frames != null && frameIndex < frames.length) {
            BufferedImage image = frames[frameIndex];
            int screenX = worldX - gp.player.worldX + gp.player.x;
            int screenY = worldY - gp.player.worldY + gp.player.y;
//            System.out.println("Bat: " + frameIndex + " !!!!");
//            System.out.println("worldX: " + worldX + " , worldY: " + worldY);
//            System.out.println("playerX: " + gp.player.worldX + " , playerY: " + gp.player.worldY);
//            g2.drawImage(frames[frameIndex], worldX, worldY, gp.tileSize, gp.tileSize, null); // Vẽ frame hiện tại ở vị trí worldX, worldY
            g2.drawImage(image, screenX, screenY, null);
        }
    }
}
