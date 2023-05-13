const questions = [
  {
    question: "Яка назва є мовою програмування ?",
    options: [
      "Java",
      "Optima",
      "Alfa",
      "Tables"
    ],
    answer: "1"
  },

  {
    question: "Що не є мережевим протоколом ?",
    options: [
      "HTTP",
      "MQTT",
      "JPA",
      "TCP"
    ],
    answer: "3"
  },

  {
    question: "Який оператор є умовним ?",
    options: [
      "FOR",
      "IF",
      "RETURN",
      "NEXT"
    ],
    answer: "2"
  }
];
let userAnswers = questions.map(opt => "0");

for(let i = 0; i < questions.length; i++) {
  let str = `№${i + 1}. ${questions[i].question}\n`
  
  for(let j = 0; j < questions[i].options.length; j++) {
    str += `${j + 1}. ${questions[i].options[j]}\n`;
  }

  userAnswers[i] = prompt(str);
  if (userAnswers[i] == null || userAnswers[i].length === 0 || isNaN(userAnswers[i]*10)) {
    i--;
  }
}

let correctAnswers = 0;
for(let i = 0; i < questions.length; i++) {
  if(questions[i].answer === userAnswers[i]) {
    correctAnswers++;
  }
}

let str = `Правильні відповіді - ваші відповіді:\n`
for(let i = 0; i < questions.length; i++) {
  str += `${i + 1}. ${questions[i].answer} - ${userAnswers[i]}\n`
}

alert(`${str}Кількість правильних відповідей: ${correctAnswers} - ${((correctAnswers * 100) / questions.length).toFixed(2)}%`);

document.location.href = '/'