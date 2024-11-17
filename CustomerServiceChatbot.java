import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomerServiceChatbot {

    private Map<String, String> englishResponses;
    private Map<String, String> spanishResponses;
    private Map<String, String> frenchResponses;
    private Map<String, String> selectedLanguageResponses;

    // Constructor
    public CustomerServiceChatbot(String language) {
        englishResponses = new HashMap<>();
        spanishResponses = new HashMap<>();
        frenchResponses = new HashMap<>();

        loadResponses();
        setLanguage(language);
    }

    // Load responses for all languages
    private void loadResponses() {
        // English responses and keywords
        englishResponses.put("hello", "Hello! How can I assist you today?");
        englishResponses.put("pricing", "Our products start at $10. For more details, please visit our pricing page.");
        englishResponses.put("support", "I'm here to help! Can you tell me more about your issue?");
        englishResponses.put("refund", "To request a refund, please visit our Refunds page or contact our support team.");
        englishResponses.put("hours", "Our customer service hours are from 9 AM to 5 PM, Monday to Friday.");
        englishResponses.put("thank you", "You're welcome! If you have any more questions, feel free to ask.");

        // Spanish responses and keywords
        spanishResponses.put("hola", "¡Hola! ¿Cómo puedo ayudarte hoy?");
        spanishResponses.put("precios", "Nuestros productos comienzan en $10. Para más detalles, por favor visita nuestra página de precios.");
        spanishResponses.put("soporte", "¡Estoy aquí para ayudar! ¿Puedes decirme más sobre tu problema?");
        spanishResponses.put("reembolso", "Para solicitar un reembolso, visita nuestra página de Reembolsos o contacta a nuestro equipo de soporte.");
        spanishResponses.put("horas", "Nuestro horario de atención al cliente es de 9 AM a 5 PM, de lunes a viernes.");
        spanishResponses.put("gracias", "¡De nada! Si tienes más preguntas, no dudes en preguntar.");

        // French responses and keywords
        frenchResponses.put("bonjour", "Bonjour ! Comment puis-je vous aider aujourd'hui ?");
        frenchResponses.put("tarifs", "Nos produits commencent à 10 $. Pour plus de détails, veuillez visiter notre page des prix.");
        frenchResponses.put("assistance", "Je suis là pour vous aider ! Pouvez-vous m'en dire plus sur votre problème ?");
        frenchResponses.put("remboursement", "Pour demander un remboursement, veuillez consulter notre page des remboursements ou contacter notre équipe d'assistance.");
        frenchResponses.put("heures", "Nos heures de service client sont de 9h à 17h, du lundi au vendredi.");
        frenchResponses.put("merci", "Je vous en prie ! Si vous avez d'autres questions, n'hésitez pas à demander.");
    }

    // Set the language for the chatbot
    public void setLanguage(String language) {
        switch (language.toLowerCase()) {
            case "spanish":
                selectedLanguageResponses = spanishResponses;
                break;
            case "french":
                selectedLanguageResponses = frenchResponses;
                break;
            case "english":
            default:
                selectedLanguageResponses = englishResponses;
                break;
        }
    }

    // Get a response based on user input
    public String getResponse(String input) {
        input = input.toLowerCase();
        for (String keyword : selectedLanguageResponses.keySet()) {
            if (input.contains(keyword)) {
                return selectedLanguageResponses.get(keyword);
            }
        }
        return "I'm sorry, I don't have an answer for that. Please contact our support team for further assistance.";
    }

    // Greet the user in the selected language
    public String getGreeting() {
        if (selectedLanguageResponses == englishResponses) {
            return "Hello! How can I assist you today?";
        } else if (selectedLanguageResponses == spanishResponses) {
            return "¡Hola! ¿Cómo puedo ayudarte hoy?";
        } else if (selectedLanguageResponses == frenchResponses) {
            return "Bonjour! Comment puis-je vous aider aujourd'hui?";
        }
        return "Hello! How can I assist you today?";
    }

    // Ask for user's name in the selected language
    public String askForName() {
        if (selectedLanguageResponses == englishResponses) {
            return "What is your name?";
        } else if (selectedLanguageResponses == spanishResponses) {
            return "¿Cuál es tu nombre?";
        } else if (selectedLanguageResponses == frenchResponses) {
            return "Quel est votre nom ?";
        }
        return "What is your name?";
    }

    // Ask for user rating after the conversation
    public String askForRating() {
        if (selectedLanguageResponses == englishResponses) {
            return "Please rate your experience on a scale of 1 to 5.";
        } else if (selectedLanguageResponses == spanishResponses) {
            return "Por favor, califique su experiencia en una escala del 1 al 5.";
        } else if (selectedLanguageResponses == frenchResponses) {
            return "Veuillez évaluer votre expérience sur une échelle de 1 à 5.";
        }
        return "Please rate your experience on a scale of 1 to 5.";
    }

    // Main method to run the chatbot
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for their language preference
        System.out.println("Welcome! Please select a language: English, Spanish, or French.");
        System.out.print("Enter language: ");
        String language = scanner.nextLine().toLowerCase();

        // Initialize chatbot with the selected language
        CustomerServiceChatbot chatbot = new CustomerServiceChatbot(language);

        // Greet the user in the selected language
        System.out.println(chatbot.getGreeting());

        // Ask for user's name
        System.out.println(chatbot.askForName());
        String userName = scanner.nextLine();

        System.out.println("Thank you, " + userName + "! You can now start chatting with the customer service bot. Type 'exit' to end the conversation.");

        // Conversation loop
        while (true) {
            System.out.print(userName + ": ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                // Ask for user rating after conversation ends
                System.out.println(chatbot.askForRating());
                String userRating = scanner.nextLine();

                System.out.println("Chatbot: Thank you for chatting with us, " + userName + "! You rated your experience as: " + userRating + ". Goodbye!");
                break;
            }

            String response = chatbot.getResponse(userInput);
            System.out.println("Chatbot: " + response);
        }

        scanner.close();
    }
}