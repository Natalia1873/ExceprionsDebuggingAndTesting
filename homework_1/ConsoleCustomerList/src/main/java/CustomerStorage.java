import exceptions.WrongCustomerDataException;
import exceptions.WrongEmailFormatException;
import exceptions.WrongPhoneFormatException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CustomerStorage {
    private static final Logger LOGGER = LogManager.getLogger(CustomerStorage.class);
    private final Map<String, Customer> storage;


    private static final int INDEX_NAME = 0;
    private static final int INDEX_SURNAME = 1;
    private static final int INDEX_EMAIL = 2;
    private static final int INDEX_PHONE = 3;


    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+7\\d{10}$");

    public CustomerStorage() {
        storage = new HashMap<>();
        LOGGER.info("Создано новое хранилище клиентов");
    }

    public void addCustomer(String data) {
        try {
            LOGGER.info("Получен запрос на добавление клиента: {}", data);

            if (data == null || data.isEmpty()) {
                throw new WrongCustomerDataException("Пустые данные клиента");
            }

            String[] components = data.split("\\s+");

            if (components.length != 4) {
                throw new WrongCustomerDataException("Неверное количество данных. Формат: Имя Фамилия Email Телефон");
            }

            validateEmail(components[INDEX_EMAIL]);
            validatePhone(components[INDEX_PHONE]);

            String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];

            if (storage.containsKey(name)) {
                throw new WrongCustomerDataException("Клиент с таким именем уже существует");
            }

            storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
            LOGGER.info("Клиент успешно добавлен: {}", name);

        } catch (Exception e) {
            LOGGER.error("Ошибка при добавлении клиента: {}", e.getMessage());
            throw e;
        }
    }

    private void validateEmail(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new WrongEmailFormatException("Неверный формат email: " + email);
        }
    }

    private void validatePhone(String phone) {
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new WrongPhoneFormatException("Неверный формат телефона. Используйте формат: +7 и 10 цифр");
        }
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}