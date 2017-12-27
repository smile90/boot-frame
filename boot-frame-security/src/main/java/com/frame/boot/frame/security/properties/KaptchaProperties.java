package com.frame.boot.frame.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码
 * @author duancq
 * 2016年8月6日 下午5:09:28
 */
@Configuration
@ConfigurationProperties(prefix = "kaptcha")
public class KaptchaProperties {


    /** 请求url */
    private String url = "/static/validCode";
    /** session中的key */
    private String sessionKey = "validCode.code";
    /** form中的name要与该地方一致 */
    private String formName = "validCode";

    /** 边框 */
    private String border = "no";

    /** 渲染效果（水纹：WaterRipple；鱼眼：FishEyeGimpy；阴影：ShadowGimpy） */
    private String obscurificator = "com.google.code.kaptcha.impl.WaterRipple";

    /** 干扰项 */
    private String noise = "com.google.code.kaptcha.impl.DefaultNoise";

    private Image image = new Image();
    private Font font = new Font();
    private Chars chars = new Chars();
    private Background background = new Background();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getObscurificator() {
		return obscurificator;
	}

	public void setObscurificator(String obscurificator) {
		this.obscurificator = obscurificator;
	}

	public String getNoise() {
		return noise;
	}

	public void setNoise(String noise) {
		this.noise = noise;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Chars getChars() {
		return chars;
	}

	public void setChars(Chars chars) {
		this.chars = chars;
	}

	public Background getBackground() {
		return background;
	}

	public void setBackground(Background background) {
		this.background = background;
	}

	public class Image {
    	private int width = 150;
    	private int height = 40;
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
    }

    public class Font {
    	private int size = 30;
    	private String color = "black";
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size = size;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
    }

    public class Chars {
    	private int length = 6;
    	private int space = 5;
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}
		public int getSpace() {
			return space;
		}
		public void setSpace(int space) {
			this.space = space;
		}
    }

    public class Background {
    	private String from = "247,247,247";
    	private String to = "247,247,247";
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KaptchaProperties{");
        sb.append("url='").append(url).append('\'');
        sb.append(", sessionKey='").append(sessionKey).append('\'');
        sb.append(", formName='").append(formName).append('\'');
        sb.append(", border='").append(border).append('\'');
        sb.append(", obscurificator='").append(obscurificator).append('\'');
        sb.append(", noise='").append(noise).append('\'');
        sb.append(", image=").append(image);
        sb.append(", font=").append(font);
        sb.append(", chars=").append(chars);
        sb.append(", background=").append(background);
        sb.append('}');
        return sb.toString();
    }
}
