package niot.imoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by arnab on 1/18/2018.
 */

public class ViewFlipperItem {

    public String img_id;
    private int avatar;
    private String nickname;
    private int background;
    private List<String> interests = new ArrayList<>();

    public ViewFlipperItem(int avatar, String nickname, int background, String... interest) {
        this.avatar = avatar;
        this.nickname = nickname;
        this.background = background;
        interests.addAll(Arrays.asList(interest));
    }

    public int getAvatar() {
        return avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public int getBackground() {
        return background;
    }

    public List<String> getInterests() {
        return interests;
    }

}
