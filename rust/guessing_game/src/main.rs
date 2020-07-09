use rand::Rng;
use std::cmp::Ordering;
use std::io;

fn main() {
    println!("Guess the number!");

    // rng is local to the current thread of execution and seeded by the OS
    let secret_number = rand::thread_rng().gen_range(1, 101);

    loop {
        println!("Please input your guess.");

        let mut guess = String::new();

        // io::Result is an enum with 2 types/arms, namely:
        // 1. oK
        // 2. Err
        io::stdin()
            .read_line(&mut guess)
            .expect("Failed to read line");

        // shadowing guess
        let guess: u32 = match guess.trim().parse() {
            Ok(num) => num,
            Err(_) => continue,
        };

        println!("You guessed: {}", guess);

        // Ordering is an enum with 3 types/arms, namely:
        // 1. Less
        // 2. Greater
        // 3. Equal
        match guess.cmp(&secret_number) {
            Ordering::Less => println!("Too Small!"),
            Ordering::Greater => println!("Too big!"),
            Ordering::Equal => {
                println!("You win!");
                break;
            }
        }
    }
}
