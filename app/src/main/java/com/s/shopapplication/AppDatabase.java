package com.s.shopapplication;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;
import android.os.AsyncTask;

@Database(entities = {Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract ProductDao productDao();

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProductDao productDao;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        private PopulateDbAsyncTask(@NonNull AppDatabase db) {
            productDao = db.productDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDao.insert(new Product(
                    "Батон Черемушки Нарезной пшеничный в нарезке 400 г",
                    "https://imgproxy.sbermarket.ru/imgproxy/size-1646-1646/czM6Ly9jb250ZW50LWltYWdlcy1wcm9kL3Byb2R1Y3RzLzE5OTk4L29yaWdpbmFsLzEvMjAyMy0xMS0yNFQxNiUzQTI5JTNBMTIuNjI4MTk0JTJCMDAlM0EwMC8xOTk5OF8xLmpwZw==.png",
                    47.4,
                    "Традиционный батон из пшеничной муки высшего сорта, вырабатывается по ГОСТ. Аппетитная, золотистая корочка и знакомый всем вкус. Идеальная основа для любимых бутербродов. 100% натуральный продукт – без улучшителей и консервантов.\n"
            ));
            productDao.insert(new Product(
                    "Хлеб Ржаной край ржано-пшеничный в нарезке 300 г",
                    "https://imgproxy.sbermarket.ru/imgproxy/size-1646-1646/czM6Ly9jb250ZW50LWltYWdlcy1wcm9kL3Byb2R1Y3RzLzI0MTA4L29yaWdpbmFsLzEvMjAyMy0xMS0yNFQxNiUzQTMxJTNBMzYuNzA5MTgyJTJCMDAlM0EwMC8yNDEwOF8xLmpwZw==.png",
                    81.9,
                    "Ароматный заварной хлеб с солодом и ржаной закваской. Хлеб богат натуральными витаминами группы В. Изготовлен из 100% натуральных ингредиентов. Выпускается в бумажно-ламинированной упаковке.\n"
            ));
            productDao.insert(new Product(
                    "Молоко 3,7% пастеризованное 930 мл Домик в Деревне Отборное БЗМЖ",
                    "https://imgproxy.sbermarket.ru/imgproxy/size-1646-1646/czM6Ly9jb250ZW50LWltYWdlcy1wcm9kL3Byb2R1Y3RzLzEwMTgyL29yaWdpbmFsLzEvMjAyMy0xMi0wN1QxNyUzQTI4JTNBMTguNzYzMzgwJTJCMDAlM0EwMC8xMDE4Ml8xLmpwZw==.png",
                    89.4,
                    "Молоко «Домик в деревне» – 100% натуральный продукт, приготовленный по традиционным рецептам. ●100% натуральное молоко. Без добавления консервантов и красителей. ●Молоко отборное подходит для тех, кто любит традиционный вкус молока ●Пить молоко «Домик в деревне» можно холодным и теплым. ●Идеально для кофе, чая и приготовления ваших любимых блюд\n"
            ));
            productDao.insert(new Product(
                    "Творог рассыпчатый Простоквашино 9% БЗМЖ",
                    "https://imgproxy.sbermarket.ru/imgproxy/size-1646-1646/czM6Ly9jb250ZW50LWltYWdlcy1wcm9kL3Byb2R1Y3RzLzE1MjQxNzcxL29yaWdpbmFsLzEvMjAyNC0wMy0wN1QxNCUzQTU4JTNBMDcuMjE1ODM5JTJCMDAlM0EwMC8xNTI0MTc3MV8xLmpwZw==.png",
                    129.90,
                    "В основе творога Простоквашино используются натуральное молоко и закваски молочнокислых культур. Такой творог можно употреблять в чистом виде, также он отлично сочетается с вареньем, медом, свежими фруктами и ягодами. Незаменимый ингредиент для приготовления сырников, блинов и запеканок. Творог \"Простоквашино\" обладает 5 гарантиями качества: 1. 100% натуральные ингредиенты 2. Неизменно отличный вкус 3. Строгий контроль на антибиотики 4. Без консервантов  5. Без растительных жиров.\n"
            ));
            productDao.insert(new Product(
                    "Сыр полутвердый Grunlander нарезка 50% БЗМЖ 150 г",
                    "https://imgproxy.sbermarket.ru/imgproxy/size-1646-1646/czM6Ly9jb250ZW50LWltYWdlcy1wcm9kL3Byb2R1Y3RzLzQ1MTczNS9vcmlnaW5hbC8xLzIwMjMtMTItMjdUMTQlM0E1MCUzQTU4LjE2ODU5MiUyQjAwJTNBMDAvNDUxNzM1XzEuanBn.png",
                    182.2,
                    "Сыр Hochland Грюнландер полутвердый, нарезка, 50%, 150 г — это универсальный полутвердый сыр с мягким сливочным вкусом. Он отлично подойдет для приготовления завтраков, а также холодных и горячих блюд для всей семьи. Сыр Grunlander производится по технологии естественного созревания из отборных ингредиентов: молока и натуральных заквасок без добавления консервантов. Он зреет ровно столько, сколько заложено природой. Именно поэтому сыр получается таким вкусным. Естественно созревает - естественно вкусно! А удобный формат нарезки идеально подходит для приготовления быстрых перекусов, бутербродов, бургеров и сэндвичей, отлично гармонируют с полезными закусками с авокадо и цельнозерновым хлебом. Нарезка Грюнландер 150 г представлена в удобной упаковке с возможностью повторного закрытия, что позволяет сохранять вкус и аромат продукта дольше.\n"
            ));
            productDao.insert(new Product(
                    "Сыр полутвердый Ламбер 50% 230 г",
                    "https://imgproxy.sbermarket.ru/imgproxy/size-1646-1646/czM6Ly9jb250ZW50LWltYWdlcy1wcm9kL3Byb2R1Y3RzLzEwMzk5L29yaWdpbmFsLzEvMjAyNC0wMi0wNlQwNyUzQTIwJTNBNDMuNTk4NTk4JTJCMDAlM0EwMC8xMDM5OV8xLmpwZw==.png",
                    269.9,
                    "Ламбер – это настоящий сыр, производимый из натурального молока. Именно поэтому он такой вкусный, и каждый его кусочек 30г содержит столько же белка, сколько целый стакан молока. Сыр Ламбер хорош как самостоятельное блюдо, а также для приготовления бутербродов, закусок, холодных и горячих блюд. Импровизируйте и наслаждайтесь! ●Из 100% натурального молока ●Не содержит растительных жиров, трансжиров и заменителей молочного жира ●Классический сливочный вкус ●Удобный формат упаковки\n"
            ));
            productDao.insert(new Product(
                    "Колбаса сырокопченая Черкизово премиум Сальчичон с розовым перцем 300 г",
                    "https://imgproxy.sbermarket.ru/imgproxy/size-1646-1646/czM6Ly9jb250ZW50LWltYWdlcy1wcm9kL3Byb2R1Y3RzLzIyNjg4NTcyL29yaWdpbmFsLzEvMjAyMy0xMS0yNFQxNSUzQTI3JTNBNDEuNzUxMTA5JTJCMDAlM0EwMC8yMjY4ODU3Ml8xLmpwZw==.png",
                    569.9,
                    "Сальчичон с розовым перцем от Черкизово премиум - это изысканное сочетание сырокопченой колбасы с пикантным вкусом розового перца. Упаковка весом 300 г подходит для разнообразных приемов пищи.\n"
            ));
            productDao.insert(new Product(
                    "Макаронные изделия Barilla Penne Rigate № 73 450 г",
                    "https://imgproxy.sbermarket.ru/imgproxy/size-1646-1646/czM6Ly9jb250ZW50LWltYWdlcy1wcm9kL3Byb2R1Y3RzLzI3MjAwMi9vcmlnaW5hbC8xLzIwMjMtMTEtMzBUMTglM0E1MyUzQTM1Ljg4MTEwNiUyQjAwJTNBMDAvMjcyMDAyXzEuanBn.png",
                    129.9,
                    "Barilla «Пенне Ригате» № 73 – прекрасный выбор для сытного обеда или ужина. Эта популярная паста выполнена в форме трубочек с полостью внутри и срезанными по диагонали краями, напоминающими наконечники старинных ручек, за что эти макароны еще называют перьями. Для производства макарон Barilla Penne Rigate № 73 используются исключительно натуральные ингредиенты: очищенная питьевая вода и высококачественная мука из твердых сортов пшеницы. В них нет ароматизаторов и искусственных добавок, именно поэтому перья обладают насыщенным натуральным вкусом и составляют изысканные сочетания с мясом, птицей, овощами, другими продуктами и соусами.\n"
            ));
            productDao.insert(new Product(
                    "Фарш говяжий Мираторг охлажденный 400 г",
                    "https://imgproxy.sbermarket.ru/imgproxy/size-1646-1646/czM6Ly9jb250ZW50LWltYWdlcy1wcm9kL3Byb2R1Y3RzLzMzNTQ2L29yaWdpbmFsLzEvMjAyNC0wMy0yOVQxMiUzQTM5JTNBMTQuODUzMzI3JTJCMDAlM0EwMC8zMzU0Nl8xLmpwZw==.png",
                    315.9,
                    "Говяжий фарш от ТМ «Мираторг» — 100% говядина и ничего лишнего! Это рубленое мясо бычков породы Black Angus, выращенных в Брянской области, станет основой для множества блюд и, что важно, позволит приготовить их быстро. Фрикадельки для супа, начинка для мясного пирога, соус болоньезе, люля, тефтели, котлеты и бифштексы — с этим полуфабрикатом вы можете в полной мере проявить фантазию, не тратя время на долгую подготовку.\n"
            ));
            productDao.insert(new Product(
                    "Батончик Nattys&Go Brownie шоколадный с арахисовой пастой и какао 45 г",
                    "https://imgproxy.sbermarket.ru/imgproxy/size-1646-1646/czM6Ly9jb250ZW50LWltYWdlcy1wcm9kL3Byb2R1Y3RzLzIwNzE3MTA2L29yaWdpbmFsLzEvMjAyMy0xMS0yOFQxNyUzQTU2JTNBMzYuOTg1OTExJTJCMDAlM0EwMC8yMDcxNzEwNl8xLmpwZw==.png",
                    94.2,
                    "Шоколадный батончик Nattys&Go - это батончик, покрытый слоем настоящего бельгийского молочного шоколада, без добавления сахара, очень вкусный, полезный и абсолютно натуральный перекус, который доставит много удовольствия и зарядит энергией!\n"
            ));
            return null;
        }
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new PopulateDbAsyncTask(instance).execute();
            super.onCreate(db);
        }
    };

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "shop_application_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
}
