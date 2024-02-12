import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @Author: Jay<jin0201@foxmail.com>.
 * @Date: 2024/2/12-19:35
 * @Description: 
 */
public class Start {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(input);
        if (!input.contains(" ")) {
            System.out.println("请以空格分隔");
            System.exit(0);
        }
        String[] arr_input = input.split(" ");
        if (arr_input.length != 4) {
            System.out.println("请输入4个随机数字");
            System.exit(0);
        }

        List<Integer> num_list = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            num_list.add(Integer.valueOf(arr_input[i % 4]));
        }
        System.out.println("最终得到的数列集合：" + num_list);

        // 1.随机生成姓名长度
        int name_length = RandomUtil.randomInt(1, 20);
        System.out.println("姓名长度：" + name_length);
        for (int i = 0; i < name_length; i++) {
            Integer first_num = num_list.get(0);
            num_list.remove(0);
            num_list.add(7, first_num);
            System.out.println("第" + (i + 1) + "次姓名位数移位的数列集合：" + num_list);
        }
        System.out.println("执行完第一步(姓名位数移位)操作后的数列集合：" + num_list);

        // 2.前三个数字插入后面的任何一个位置
        // 随机生成除前三个数字外的任意一个位置
        int random_pos = RandomUtil.randomInt(1, 5);
        System.out.println("前三个数字将移动到后五个数字当中的位置：" + random_pos);
        Integer second_first_num = num_list.remove(0);
        Integer second_num = num_list.remove(0);
        Integer third_num = num_list.remove(0);
        System.out.println("前三个数字：" + second_first_num + "," + second_num + "," + third_num);
        num_list.add(random_pos, second_first_num);
        num_list.add(random_pos + 1, second_num);
        num_list.add(random_pos + 2, third_num);
        System.out.println("执行完第二步(前三个数字插入后面的任何一个位置)操作后的数列集合：" + num_list);

        // 3.第一张拿走藏起来
        Integer first_num = num_list.remove(0);
        System.out.println("执行完第三步(藏起第一张)后的数列集合：" + num_list);

        // 4.南方人拿一张，北方人拿两张，不确定拿三张，插入到剩下的中间
        int random_south_or_nurth = RandomUtil.randomInt(1, 4);
        int random_pos_south_or_nurth = 1;
        List<Integer> remove_list = new LinkedList<>();
        switch (random_south_or_nurth) {
            case 1:
                // 南方人
                random_pos_south_or_nurth = RandomUtil.randomInt(1, 6);
                remove_list.add(num_list.remove(0));
                break;
            case 2:
                // 北方人
                random_pos_south_or_nurth = RandomUtil.randomInt(1, 5);
                remove_list.add(num_list.remove(0));
                remove_list.add(num_list.remove(0));
                break;
            case 3:
                // 不确定
                random_pos_south_or_nurth = RandomUtil.randomInt(1, 4);
                remove_list.add(num_list.remove(0));
                remove_list.add(num_list.remove(0));
                remove_list.add(num_list.remove(0));
                break;
        }
        for (int i = 0; i < random_south_or_nurth; i++) {
            num_list.add(random_pos_south_or_nurth++, remove_list.get(i));
        }
        System.out.println("执行完第四步(南方人拿一张，北方人拿两张，不确定拿三张)操作后的数列集合：" + num_list);

        // 5.男生仍一张，女生仍两张
        int random_male_or_female = RandomUtil.randomInt(1, 3);
        for (int i = 0; i < random_male_or_female; i++) {
            num_list.remove(0);
        }
        System.out.println("执行完第五步(男生仍一张，女生仍两张)操作后的数列集合：" + num_list);

        // 6.见证奇迹的时刻
        for (int i = 0; i < 7; i++) {
            int size = num_list.size();
            Integer sixth_first_num = num_list.remove(0);
            num_list.add(size - 1, sixth_first_num);
            System.out.println("见证奇迹的时刻，第" + (i + 1) + "次：" + num_list);
        }
        // 7.好运留下来，烦恼丢出去(先移动第一张到最后，再丢弃第一张)，直到最后一张
        int size = num_list.size();
        for (int i = 0; i < size - 1; i++) {
            Integer seventh_first_num = num_list.remove(0);
            num_list.add(num_list.size(), seventh_first_num);

            num_list.remove(0);
            System.out.println("好运留下来，烦恼丢出去，第" + (i + 1) + "次：" + num_list);
        }
        System.out.println("藏起来的数字是：" + first_num + "；最后剩下的数字是：" + num_list.get(0) + "；是否相等：" + (first_num == num_list.get(0) ? "是" : "否"));
    }
}
