package com.example.template.Database;

import android.content.Context;

import com.example.template.R;
import com.example.template.object.Exercise;
import com.example.template.object.Muscle;
import com.example.template.object.Plan;
import com.example.template.object.User;

import java.util.ArrayList;
import java.util.List;


public class InsertData {
    Context mContext;
    public InsertData(Context mContext) {
        this.mContext = mContext;
    }

    public void insertMuscle(){
        List<Muscle> list = new ArrayList<>();
        list.add(new Muscle(1, "Chest", R.drawable.chest));
        list.add(new Muscle(2, "Back", R.drawable.back));
        list.add(new Muscle(3, "Shoulder", R.drawable.shoulder));
        list.add(new Muscle(4, "Biceps", R.drawable.biceps));
        list.add(new Muscle(5, "Triceps", R.drawable.triceps));
        list.add(new Muscle(6, "Abs", R.drawable.abs));
        list.add(new Muscle(7, "Legs", R.drawable.legs));

        for(Muscle muscle : list){
            FitnessDatabase.getInstance(mContext).muscleDao().insertMuscle(muscle);
        }
    }
    public void insertPlan(){
        List<Plan> list = new ArrayList<>();
        list.add(new Plan(1, "weight gain", 1));
        list.add(new Plan(2, "weight loss", 1));
        list.add(new Plan(3, "muscle gain", 1));
        list.add(new Plan(4, "new", 1));
        for(Plan plan : list){
            FitnessDatabase.getInstance(mContext).planDao().insertPlan(plan);
        }
    }
    public void insertExercise(){
        List<Exercise> list = new ArrayList<>();
        //Chest Muscle
        list.add(new Exercise(1, 1, "Máy tập ngực", R.drawable.chest_1, R.raw.chest_1,"Vị trí bắt đầu: ngồi lên ghế, tì lưng vào miếng đệm. Di chuyển tay vào nhau khi thở ra. Giữ lại một giây. Trở về vị trí ban đầu khi hít vào.",1 ));
        list.add(new Exercise(2, 1, "Máy tập tạ", R.drawable.chest_2, R.raw.chest_2, "Vị trí bắt đầu: ngồi lên ghế, tì lưng vào miếng đệm. Nắm tay cầm với khoảng cách rộng hơn vai một chút. Duỗi tay ra (nhưng không khóa khớp cùi chỏ) khi thở ra. Trở về vị trí ban đầu khi hít vào. Gập tay chậm rãi tránh làm đột ngột. ",2));
        list.add(new Exercise(3, 1, "Nhúng ngực", R.drawable.chest_3, R.raw.chest_3, "Vị trí bắt đầu: đặt tay lên thanh xà, cùi chỏ hướng ra ngoài. Khi bạn hít vào - hạ người xuống. Khi bạn thở ra - trở về vị trí ban đầu. Đừng khóa khớp tay.", 3 ));
        list.add(new Exercise(4, 1, "Nằm kéo cáp", R.drawable.chest_4, R.raw.chest_4,"Vị trí bắt đầu: ngả người với lưng ép vào ghế. Tay ở phía trên người và hơi cong. Di chuyển tay về hai phía khi hít vào. Trở về vị trí ban đầu đều đặn khi thở ra.",4));
        //Back Muscle
        list.add(new Exercise(5, 2, "Bài tập Deadlift", R.drawable.back_1, R.raw.back_1,"Vị trí bắt đầu: đứng hơi khuỵu gối, bàn chân song song với nhau và khoảng cách hơi chân rộng bằng vai hoặc hẹp hơn. Giữ lưng hơi cúi và hơi nghiêng người về trước. Giữ thanh tạ với kiểu cầm mixed. Khi thở ra nâng thanh tạ đều đặn và chậm rãi sao cho hông và người song song với thanh tạ. Kéo vai sau lại với nhau, hít vào và hạ thanh tạ từ từ cho đến khi nó chạm sàn (nhưng không được để nó trên sàn).",1));
        list.add(new Exercise(6, 2, "Gánh tạ cuí đầu", R.drawable.back_2, R.raw.back_2,"Vị trí bắt đầu: đứng với chân rộng bằng vai, đặt thanh tạ lên vai ở sau đầu, đầu gối hơi khuỵu, cúi lưng. Nghiêng người về phía trước khi bạn hít vào - cho đến khi nó gần song song với sàn nhà. Trở về vị trí ban đầu khi bạn thở ra.", 2));
        list.add(new Exercise(7, 2, "Kéo xà đơn 1", R.drawable.back_3, R.raw.back_3,"Vị trí bắt đầu: treo người trên thanh đòn; tay bám thanh đòn phải rộng hơn vai, cong lưng. Kéo người lên xà khi thở ra. Lúc lên xà cố gắng nghiêng người về sau một chút. Hạ xuống chậm rãi khi hít vào (nhưng đừng xuống hết hoàn toàn).", 3));
        list.add(new Exercise(8, 2, "Kéo xà đơn 2", R.drawable.back_4, R.raw.back_4,"Vị trí bắt đầu: giữ thanh xà hẹp tay, treo người lên thanh xà. Khi kéo người lên xà - thở ra, khi trở về vị trí ban đầu - hít vào.",4));
        //Shoulder Muscle
        list.add(new Exercise(9, 3, "Banh ngực ngược", R.drawable.shoulder_1, R.raw.shoulder_1,"Vị trí bắt đầu: ngồi lên máy, giữ tay phía trước mặt. Khi bạn thở ra - di chuyển tay ra hai bên một cách đều đặn, giữ lại một giây và trở về vị trí ban đầu khi hít vào.", 1));
        list.add(new Exercise(10, 3, "Chèo tạ đòn thẳng", R.drawable.shoulder_2, R.raw.shoulder_2,"Vị trí bắt đầu: đứng hơi khuỵu gối, di chuyển người về phía trước một chút và hơi cúi lưng dưới. Giữ tay cầm với khoảng rộng bằng vai. Khi duỗi tay - thở ra. Khi trở về vị trí ban đầu - hít vào.", 2));
        list.add(new Exercise(11, 3, "Kéo cáp gần mặt", R.drawable.shoulder_3, R.raw.shoulder_3,"Vị trí bắt đầu: đứng hơi cúi lưng, giữ tay cầm với khoảng hẹp hơn vai. Nâng tay lên tới cằm khi thở ra. Ghi nhớ rằng cùi chỏ của bạn nên ở cao hơn vai và hướng lên tới vị trí cằm. Trở về vị trí ban đầu khi hít vào.", 3));
        list.add(new Exercise(12, 3, "Nâng tạ", R.drawable.shoulder_4, R.raw.shoulder_4,"Đứng với chân rộng bằng hông, hai bàn tay rộng hơn vai. Cùi chỏ hơi phía trước thanh tạ. Nắm chặt thanh tạ. Hạ người xuống, hơi nghiêng đầu về sau và nhìn về phía trước. Duỗi hông và chân nhanh chóng sau đó ấn xuống. Nâng thanh tạ hơi qua đầu. Gót chân giữ trên sàn cho đến khi hông và chân duỗi ra. Động tác nên dứt khoát. Thở ra khi đứng dậy.", 4));
        //Biceps Muscle
        list.add(new Exercise(13, 4, "Bài tập cơ bắp tay", R.drawable.biceps_1, R.raw.biceps_1,"Vị trí bắt đầu: đứng với đầu gối hơi khuỵu và chân rộng bằng vai, hơi nghiêng người về trước. Nâng thanh tạ EZ lên khi thở ra, hạ thanh EZ khi hít vào.", 1));
        list.add(new Exercise(14, 4, "Cuốn tay trước", R.drawable.biceps_2, R.raw.biceps_2,"Vị trí bắt đầu: giữ đầu gối cố định, lưng thẳng. Khi thở ra - nâng một bên tạ lên, khi hít vào - hạ tạ về vị trí ban đầu. Lặp lại với tay còn lại.", 2));
        list.add(new Exercise(15, 4, "Cuốn tạ tập trung", R.drawable.biceps_3, R.raw.biceps_3,"Vị trí bắt đầu: ngồi lên ghế, một tay vuông góc với sàn nhà, cùi chỏ của tay đó hơi gập, lòng bàn tay hướng vào người. Thở ra vừa nâng tạ lên vừa xoay tay sao cho lòng bàn tay hướng lên trên. Trở về vị trí ban đầu khi hít vào.", 3));
        list.add(new Exercise(16, 4, "Kéo cáp tay trước", R.drawable.biceps_4, R.raw.biceps_4,"Vị trí bắt đầu: đứng, nắm tay cầm với lòng bàn tay hướng vào trong. Thở ra và gập tay về phía đầu cùng lúc xoay tay sao cho lòng bàn tay hướng xuống. Không được di chuyển cùi chỏ. Hít vào và trở về vị trí ban đầu.", 4));
        //Triceps Muscle
        list.add(new Exercise(17, 5, "Kéo cáp tay sau", R.drawable.triceps_1, R.raw.triceps_1,"Vị trí bắt đầu: đứng với đầu gối hơi khuỵu và di chuyển người về phía trước một chút, hơi cúi lưng. Giữ tay cầm với khoảng cách hẹp hơn vai. Cố gắng duỗi tay và gần chạm vào chân. Khi duỗi tay thở ra. Khi trở về vị trí ban đầu thì hít vào.", 1));
        list.add(new Exercise(18, 5, "Kéo cơ tay sau", R.drawable.triceps_2, R.raw.triceps_2,"Vị trí bắt đầu: đứng với đầu gối và người hơi cúi về phía trước, ép tay và cùi chỏ vào người. Giữ dây tập với lòng bàn tay hướng vào nhau. Thở ra và kéo dây xuống đồng thời đưa mỗi bên dây ra bên cạnh đùi. Giữ lại một giây, tại vị trí căng nhất. Hít vào và đưa dây lên chậm rãi về vị trí ban đầu.", 2));
        list.add(new Exercise(19, 5, "Nâng tạ đôi sau đầu", R.drawable.triceps_3, R.raw.triceps_3,"Vị trí bắt đầu: đứng, duỗi tay cầm tạ đơn trên đầu để cả cánh tay vuông góc với sàn nhà và kế bên đầu. Dùng tay không cầm tạ giữ phần tay sau của tay đang cầm tạ. Khi bạn hít vào hạ tay cầm tạ đều đặn xuống sau đầu cho đến khi cùi chỏ tạo góc 90 độ. Giữ yên tay. Thở ra và trở về vị trí ban đầu.", 3));
        list.add(new Exercise(20, 5, "Tập tăng bắp tay", R.drawable.triceps_4, R.raw.triceps_4,"Vị trí bắt đầu: ngả lưng trên ghế ngang, giữ toàn bộ lưng đặt trên ghế, nắm thanh EZ hẹp tay. Hạ thanh EZ xuống - nó nên xuống gần chạm đầu - hơi cao hơn một chút sao với trán. Sau đó thở ra và đưa tạ về vị trí ban đầu.", 4));
        //Abs Muscle
        list.add(new Exercise(21, 6, "Giun đũa", R.drawable.abs_1, R.raw.abs_1,"Đứng bằng hai chân của bạn với nhau. Rướn người về phía trước và chạm tay xuống sàn. Tùy thuộc vào độ linh hoạt của gân kheo, bạn có thể cần phải uốn cong đầu gối một chút (hoặc nhiều!) Để thực hiện động tác này. Co cơ bụng lại và đưa tay về phía trước trong khi vẫn duy trì sự ổn định cốt lõi của bạn cho đến khi bạn ở tư thế plank. Đừng để hông của bạn bị tụt xuống. Giữ trong vài giây và bắt đầu đưa tay ra sau. Trở lại vị trí bắt đầu và lặp lại.", 1));
        list.add(new Exercise(22, 6, "Đạp xe", R.drawable.abs_2, R.raw.abs_2,"Vị trí bắt đầu: ngả người lên sàn, đặt tay dưới mông. Giữ chân hơi cong phía trên sàn. Thực hiện động tác giống như bạn đang đạp xe.", 2));
        list.add(new Exercise(23, 6, "Cắm ván", R.drawable.abs_3, R.raw.abs_3,"Vị trí bắt đầu: nằm về một phía và tựa lên cùi chỏ, giữ tay vuông góc với sàn nhà. Tay còn lại ép sát người. Đầu gối và hông phải thẳng. Một chân đặt lên chân còn lại.", 3));
        list.add(new Exercise(24, 6, "Gập bụng", R.drawable.abs_4, R.raw.abs_4,"Bắt đầu bằng tư thế plank cao cổ điển. Siết cơ mông và tập trung vào phần cơ của bạn. Cơ thể bạn phải tạo thành một đường thẳng, lưng phẳng và không để hông hạ xuống. Hơi uốn cong ở đầu gối và nhảy cả hai chân sang hai bên như thể thực hiện động tác giậm nhảy. Tiếp đất bằng ngón chân, cho phép đầu gối hơi khuỵu lại, sau đó nhảy chân trở lại vị trí ban đầu.", 4));
        //Legs Muscle
        list.add(new Exercise(25, 7, "Chạy nâng cao đùi", R.drawable.legs_1, R.raw.legs_1,"Vị trí bắt đầu: đứng chụm chân lại, cúi lưng, gập tay. Chạy tại chỗ, nâng đầu gối lên vị trí hông và tiếp đất bằng mũi chân.", 1));
        list.add(new Exercise(26, 7, "Cầm tạ đứng tấn ngang", R.drawable.legs_2, R.raw.legs_2,"Vị trí bắt đầu: đứng với mỗi tay cầm tạ đơn. Chân rộng bằng vai. Nghiêng ngực về phía trước một chút và bước lui về phía ngược lại, hạ thấp người và để đầu gối gần chạm sàn. Trở về vị trí ban đầu và đổi chân.", 2));
        list.add(new Exercise(27, 7, "Cầm tạ đứng tấn trước", R.drawable.legs_3, R.raw.legs_3,"Vị trí bắt đầu: đứng ở tư thế hẹp. Khi bạn hít vào, bước về phía trước một bước và hạ thấp người cho đến khi đùi trước song song với sàn và đầu gối hai chân khuỵu xuống một góc 90 độ. Chân phía sau chạm đất bằng mũi chân. Thở ra và trở về vị trí ban đầu. Thay đổi chân lần lượt.", 3));
        list.add(new Exercise(28, 7, "Gánh đùi", R.drawable.legs_4, R.raw.legs_4,"Vị trí bắt đầu: đứng với chân rộng bằng vai, giữ tay dọc người, cúi lưng. Squat và giơ thẳng tay trước mặt khi hít vào. Hông nên xuống dưới thấp hơn đầu gối một chút. Khi thở ra đứng dậy và trở về vị trí ban đầu.", 4));

        for(Exercise exercise : list){
            FitnessDatabase.getInstance(mContext).exerciseDao().insertExercise(exercise);
        }
    }
}
