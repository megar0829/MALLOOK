import levelOne from "@/assets/img/profile/level1.png";
import levelTwo from "@/assets/img/profile/level2.png";
import levelThree from "@/assets/img/profile/level3.png";
import levelFour from "@/assets/img/profile/level4.png";
import levelFive from "@/assets/img/profile/level5.png";
import levelSix from "@/assets/img/profile/level6.png";
import levelSeven from "@/assets/img/profile/level7.png";

export default function GetProfileImage(level: string) {
    if (level === "LEVEL1") {
        return levelOne;
    } else if (level === "LEVEL2") {
        return levelTwo;
    } else if (level === "LEVEL3") {
        return levelThree;
    } else if (level === "LEVEL4") {
        return levelFour;
    } else if (level === "LEVEL5") {
        return levelFive;
    } else if (level === "LEVEL6") {
        return levelSix;
    } else {
        return levelSeven;
    }
}