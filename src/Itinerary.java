import java.util.ArrayList;
import java.util.Scanner;

/**
 * 旅行の日程を求めるクラス
 */
public class Itinerary {
	public static void main(String[] args) {
		ArrayList<String> ary = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		//一行目に休日の日数と旅行の日数を入力する
		String first_line = sc.nextLine();
		//休日日数と旅行日数を半角スペースで区切って配列に格納する
		String[] strs = first_line.split(" ");
		//一行目の最初の入力値（旅行の日数）を変数vacationにセットする
		int vacation = Integer.parseInt(strs[0]);
		//一行目の二番目の入力値（休日の日数）を変数travel_datesにセットする
		int travel_dates = Integer.parseInt(strs[1]);
		/*
		*二行目以降の日付と降水確率を入力する
		*休日と旅行日数が30日以下で、休日が旅行日数より多いことを確認
		*/
		try {
			if (2 <= travel_dates && travel_dates <= 30 && travel_dates <= vacation ) {
				for (int i = 0; i < vacation; i++) {
					if (i < vacation) {
						Scanner sc2 = new Scanner(System.in);
						String second_line = sc2.nextLine();
						//休日日数と旅行日数を半角スペースで区切って配列に格納する
						String[] strs2 = second_line.split(" ");
						for (String s : strs2) {
							ary.add(s);
						}
					}
				}
			} else {
				System.out.println("休日か旅行日数に誤りがあります");
				return;
			}
			//日付が正しく連続していることの確認
			int[] array = new int[vacation * 2];
			for (int i = 0, k = 0; i < array.length; i +=2,k++) {
				array[i] = Integer.parseInt(ary.get(i));
					array[k] = array[i];
			}
			for (int j = 1; j < array.length/2; j++) {
				if (array[j] - 1 == array[j - 1]) {
					continue;
				} else {
					System.out.println("日付に誤りがあります");
					return;
				}
			}
			//降水確率が0以上100以下になっていることを確認
			int array_rainy_percent[] = new int[vacation * 2];
			for (int i = 1, k = 0; i < vacation * 2; i++, k++) {
				int rainy_percent = Integer.parseInt(ary.get(i));
				if (0 <= rainy_percent && rainy_percent <= 100) {
					array_rainy_percent[k] = rainy_percent;
				} else {
					System.out.println("降水確率に誤りがあります");
					return;
				}
				i++;
			}
			//休日の降水確率の合計を求める
			int min_day = 0;
			int min = 99999;
			int sum_rainy_percent[] = new int[vacation];
			for(int i = 0; i <= vacation - travel_dates; i++) {
				int tmp_sum_rainy_percent = 0;
				//変数tmp_sum_rainy_percentに一時的にtravel_dates日間の降水確率の合計を代入
				for(int k = 0; k < travel_dates; k++) {
					tmp_sum_rainy_percent += array_rainy_percent[k + i];
				}
				//travel_dates日間の降水確率の合計が一番少ない値を求める
				for(int j = 0; j < 1; j++) {
					sum_rainy_percent[i] = tmp_sum_rainy_percent;
					if (min > sum_rainy_percent[i]) {
						min = sum_rainy_percent[i];
						min_day = i;
					}
				}
			}
			//降水確率の合計が一番少ない期間を出力
			System.out.print("\n");
			System.out.print(ary.get(min_day * 2));
			System.out.print(" ");
			String last_min_day = null ;
			if (travel_dates > 2) {
				last_min_day = ary.get((min_day * 2) + (travel_dates * 2 - 2));
				System.out.print(last_min_day);
			} else {
			 System.out.println(min_day + 2);
			}
		} catch (Exception e) {
			System.out.println("入力に誤りがあります");
		}
	}
}
