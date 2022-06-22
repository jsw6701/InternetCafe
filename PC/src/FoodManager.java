import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FoodManager{
    private ArrayList<Food> list;
    private ArrayList<Food> list2;
    private String file;
    HashMap<String, ArrayList<Food>> map = new HashMap<>();
    public FoodManager(String file) {
        this.file = file;
        list = new ArrayList<Food>();
        list2 = new ArrayList<Food>();
    }

    public void add(Food food) {
        list.add(food);
    }
    public void addUser(){

        list2.clear();
        System.out.print("음식 이름 : ");
        String name ="";
        Scanner sc = new Scanner(System.in);
        name = sc.next();
        for(int i=0; i< list.size(); i++){
            if(list.get(i).getName().equals(name)){
                list2.add(list.get(i));
            }
        }
    }
    public void addUser1(String name){
        map.put(name, new ArrayList<>(list2));
        System.out.println(map);
        list.clear();
        list2.clear();
    }
    public void showcart(String key){
        int total = 0;
        if(map.containsKey(key)){
            total += map.get(key).get(0).getPrice();
            System.out.println("가격은: " + total);
            System.out.print("결제하시겠습니까?(y/n) : ");
            String yn = "";
            Scanner sc1 = new Scanner(System.in);
            yn = sc1.next();
            if(yn.equals("y")){
                map.remove(key);
                System.out.println("결제가 완료되었습니다.");
            }
            else if(yn.equals("n")){
                System.out.println("결제를 취소하셨습니다.");
            }
        }
        else{
            System.out.println("회원님의 아이디를 찾을 수 없습니다.");
        }
    }
    public void show() { for (int i =0; i<list.size(); i++) System.out.println(list.get(i));}

    public void clear() {
        list.clear();
    }

    public void save() {
        try {
            FileWriter writer = new FileWriter(file);
            BufferedWriter buf = new BufferedWriter(writer);
            for (Food food : list) {
                buf.write(food.getName() + ",");
                buf.write(Integer.toString(food.getPrice()));
                buf.newLine();
            }
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buf = new BufferedReader(reader);
            String line;
            while ((line = buf.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                String name = tokenizer.nextToken();
                int price = Integer.parseInt(tokenizer.nextToken());
                add(new Food(name, price));
            }
            buf.close();
        } catch (FileNotFoundException e) {
            System.out.println("File open error : " + file + "을 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
