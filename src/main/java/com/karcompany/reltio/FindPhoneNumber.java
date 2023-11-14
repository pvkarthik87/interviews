package com.karcompany.reltio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*

[
  {
    "type": "type/Individual",
    "attributes": {
      "Phone": [
        {
          "value": {
            "Type": [
              {
                "value": "ALTERNATE_TELEPHONE"
              }
            ],
            "Number": [
              {
                "value": "12346576"
              }
            ]
          }
        }
      ]
    }
  }
]

*/


public class FindPhoneNumber {
    public static Optional<String> findNumber(List<PhoneNumberEntry> entries) {
        return entries.stream()
                .findAny()
                .map(entry -> getNumberFromAttributes(entry.attributes)).get();
    }

    private static Optional<String> getNumberFromAttributes(PhoneNumberAttributes attributes) {
        return attributes.numbers.stream()
                .findAny()
                .map(number -> readNumber(number));

    }

    private static String readNumber(Number number) {
        return number.value.Number.stream().findAny().get().value;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<PhoneNumberEntry> entries = new ArrayList<>();
        List<Number> numbers = new ArrayList<>();

        numbers.add(
                new Number(

                )
        );
        entries.add(
                new PhoneNumberEntry(
                        "type/Individual",
                        new PhoneNumberAttributes(

                        )
                )
        );
        Optional<String> result = findNumber(entries);
    }
}

record PhoneNumber(String type) {
}

class PhoneNumberEntry {
    String type;
    PhoneNumberAttributes attributes;

    public PhoneNumberEntry(String type, PhoneNumberAttributes phoneNumberAttributes) {
        this.type = type;
        this.attributes = phoneNumberAttributes;
    }
}

class PhoneNumberAttributes {
    List<Number> numbers;
}

class Number {
    NumberValue value;
}

class NumberValue {
    List<Value> type;
    List<Value> Number;
}

class Value {
    String value;
}
