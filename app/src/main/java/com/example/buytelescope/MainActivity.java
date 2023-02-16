package com.example.buytelescope;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // задание полей
    float totalTelescopePrice = 14_000; // стоимость телескопа
    int account = 1_000; // счёт пользователя
    float grants = 2_500; // стипендия в месяц
    float percentBank = 5; // годовая процентная ставка за ипотеку
    float[] monthlyPayments = new float[12]; // создание массива ежемесячных накоплений в банке на 1 год


     // метод подсчёта времени накопления необходимой суммы на покупку телескопа (сумма долга, сумма платежа, годовой процент)
    // и заполнение массива monthlyPayments[] ежемесячными платежами

    public int countMonth() {

        float percentBankMonth = percentBank / 12; // подсчет процент банка за один месяц

        float accountFirstMonth = account + account * percentBankMonth / 100; // подсчёт суммы счета с процентом банка за первый месяц, на счету пользователя

        float telescopePrice = totalTelescopePrice - accountFirstMonth; // оставшаяся сумма для накопления на телескоп

        int count = 0; // счётчик месяцев накопления на телескоп (первый месяц деньги лежат на счету пользователя
                       // в банке под проценты и ждём первую стипендию)
        float total = accountFirstMonth; //присвоил переменной total значение расчета аккаунта за первый месяц, для дальнейшего подсчета цикла

        // алгоритм расчёта накопления на телескоп
        while (total < totalTelescopePrice) {
                     // заполнение массива ежемесячными накопления
            if (total == accountFirstMonth) { // если сумма на счету равна первоначальной сумме с процентом, то
                monthlyPayments[count] = accountFirstMonth; // в массив вписываем сет аккаунта изначальный с процентом банка
            } else if (total < totalTelescopePrice){ // в массив добавляется целый платёж
                monthlyPayments[count + 1] = total;
            }
            else { // иначе
                monthlyPayments[count + 1] = total - totalTelescopePrice; // в массив добавляется платёж равный остатку долга
            }
            count++; // добавление нового месяца платежа
            // вычисление накопления с учётом стипендии и процента
            total = total + (grants + grants * percentBankMonth / 100);
        }

        return count;
    }

    // создание дополнительных полей для вывода на экран полученных значений
    private TextView countOut; // поле вывода количества месяцев накопления
    private TextView manyMonthOut; // поле выписки по ежемесячным платежам

    // вывод на экран полученных значений
    @Override
    protected void onCreate(Bundle savedInstanceState) { // создание жизненного цикла активности
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // присваивание жизненному циклу активити представления activity_main

        // присваивание переменным активити элементов представления activity_main
        countOut = findViewById(R.id.countOut); // вывод информации количества месяцев накопления
        manyMonthOut = findViewById(R.id.manyMonthOut); // вывод информации выписки по ежемесячным платежам

        // запонение экрана
        // 1) вывод количества месяцев накопления
        countOut.setText("Накопления будут " + countOut + " месяцев");
        // 2) подготовка выписки
        String monthlyPaymentsList = " ";
        for (float list : monthlyPayments) {
            if (list > 0) {
                monthlyPaymentsList = monthlyPaymentsList + Float.toString(list) + " монет ";
            } else {
                break;
            }
        }
        // 3) вывод выписки ежемесячных накоплений
        manyMonthOut.setText("Первоначальный взнос " + account + " монет, ежемесячные накопления: " + monthlyPaymentsList);
    }
}