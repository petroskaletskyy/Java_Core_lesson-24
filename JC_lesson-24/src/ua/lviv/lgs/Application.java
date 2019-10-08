package ua.lviv.lgs;

import java.io.File;
import java.util.Scanner;

public class Application {

	public static int menu() {
		System.out.println("Введіть 10 для того, щоб створити новий кінотеатр");
		System.out.println("Введіть 11 для того, щоб зберегти кінотеатр в файл");
		System.out.println("Введіть 12 для того, щоб завантажити кінотеатр із файлу");
		System.out.println("Введіть 13 для того, щоб додати фільм");
		System.out.println("Введіть 14 для того, щоб видалити фільм");
		System.out.println("Введіть 15 для того, щоб вивести всі фільми");
		System.out.println("Введіть 16 для того, щоб додати сеанс");
		System.out.println("Введіть 17 для того, щоб видалити сеанс");
		System.out.println("Введіть 18 для того, щоб вивести список сеансів на день");
		System.out.println("Введіть 19 для того, щоб вивести розклад");
		System.out.println("Введіть 0 для того, щоб вийти");

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("Зробіть Ваш вибір: ");
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
				System.out.println("Кінотеатр \"" + cinema.getName() + "\" успішно збережено в файл!\n");
				break;
			}

			case 12: {
				cinema = (Cinema) SerializeMethods.deserialize(new File("Cinema.txt"));
				System.out.println("Кінотеатр \"" + cinema.getName() + "\" успішно завантажений з файлу!\n");
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
				System.out.println("Введіть день для сеансу:");
				days = Days.inputDay();
				Movie movie = Movie.inputMovie();
				cinema.addSeance(movie, days);
				break;
			}
			case 17: {
				System.out.println("Введіть день сеансу, який потрібно видалити");
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
				System.out.println("Виводимо розклад сеансів на " + day.name());
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
				System.out.println("Дякуємо, що скористалися нашим кінотеатром. Всього найкращого\n");
				System.exit(0);
				break;
			}

			default: {
				System.out.println("Такий пункт в меню відсутній!");
				break;
			}
			}
		}
	}
}