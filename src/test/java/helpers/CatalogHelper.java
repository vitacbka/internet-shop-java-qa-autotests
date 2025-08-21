package helpers;

import java.util.Random;

import static testdata.CatalogPageTestData.ALL_CATEGORIES;

public class CatalogHelper {

    public static String getRandomCategory() {
        return ALL_CATEGORIES[new Random().nextInt(ALL_CATEGORIES.length)];
    }

    public static int getRandomProductIndex(int maxIndex) {
        return new Random().nextInt(maxIndex);
    }
}
