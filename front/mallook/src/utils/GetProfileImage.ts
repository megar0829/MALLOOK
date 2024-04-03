import levelOne from "@/assets/img/profile/level1.png";
import levelTwo from "@/assets/img/profile/level2.png";
import levelThree from "@/assets/img/profile/level3.png";
import levelFour from "@/assets/img/profile/level4.png";
import levelFive from "@/assets/img/profile/level5.png";
import levelSix from "@/assets/img/profile/level6.png";
import levelSeven from "@/assets/img/profile/level7.png";

export default function GetProfileImage(level: number) {
	if (level === 1) {
		return levelOne;
	} else if (level === 2) {
		return levelTwo;
	} else if (level === 3) {
		return levelThree;
	} else if (level === 4) {
		return levelFour;
	} else if (level === 5) {
		return levelFive;
	} else if (level === 6) {
		return levelSix;
	} else {
		return levelSeven;
	}
}