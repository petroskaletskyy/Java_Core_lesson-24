package ua.lviv.lgs;

import java.io.File;
import java.util.Scanner;

public class Application {

	public static int menu() {
		System.out.println("������ 10 ��� ����, ��� �������� ����� ��������");
		System.out.println("������ 11 ��� ����, ��� �������� �������� � ����");
		System.out.println("������ 12 ��� ����, ��� ����������� �������� �� �����");
		System.out.println("������ 13 ��� ����, ��� ������ �����");
		System.out.println("������ 14 ��� ����, ��� �������� �����");
		System.out.println("������ 15 ��� ����, ��� ������� �� ������");
		System.out.println("������ 16 ��� ����, ��� ������ �����");
		System.out.println("������ 17 ��� ����, ��� �������� �����");
		System.out.println("������ 18 ��� ����, ��� ������� ������ ������ �� ����");
		System.out.println("������ 19 ��� ����, ��� ������� �������");
		System.out.println("������ 0 ��� ����, ��� �����");

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("������ ��� ����: ");
		int menuChoise = scanner.nextInt();

		return menuChoise;
	}

	public static void main(String[] args) throws Exception {
		Cinema cinema = null;
		Days days;
		Schedule schedule;

		while (true) {
			switch (menu()) {

			case 10: {
				cinema = Cinema.inputCinema();
				break;
			}

			case 11: {
				SerializeMethods.serialize(cinema, new File("Cinema.txt"));
				System.out.println("ʳ������� \"" + cinema.getName() + "\" ������ ��������� � ����!\n");
				break;
			}

			case 12: {
				cinema = (Cinema) SerializeMethods.deserialize(new File("Cinema.txt"));
				System.out.println("ʳ������� \"" + cinema.getName() + "\" ������ ������������ � �����!\n");
				break;
			}

			case 13: {
				cinema.addMovie();
				break;
			}

			case 14: {
				Movie movie = Movie.inputMovie();
				cinema.removeMovie(movie);
				break;
			}

			case 15: {
				cinema.printAllMovie();
				break;
			}

			case 16: {
				System.out.println("������ ���� ��� ������:");
				days = Days.inputDay();
				Movie movie = Movie.inputMovie();
				cinema.addSeance(movie, days);
				break;
			}
			case 17: {
				System.out.println("������ ���� ������, ���� ������� ��������");
				days = Days.inputDay();
				for (Days day1 : Days.values()) {
					if (day1.name().equals(days.name().toUpperCase())) {
						cinema.removeSeance(day1);
					}
				}
				break;
			}

			case 18: {
				Days day = Days.inputDay();
				System.out.println("�������� ������� ������ �� " + day.name());
				schedule = cinema.getScheduleMap().get(day);
				cinema.getSchedule(schedule);
				break;
			}

			case 19: {
				for (Days day1 : Days.values()) {
					System.out.println();
					System.out.println(day1.name());
					schedule = cinema.getScheduleMap().get(day1);
					cinema.getSchedule(schedule);
				}
				break;
			}

			case 0: {
				System.out.println("������, �� ������������ ����� ����������. ������ ����������\n");
				System.exit(0);
				break;
			}

			default: {
				System.out.println("����� ����� � ���� �������!");
				break;
			}
			}
		}
	}
}