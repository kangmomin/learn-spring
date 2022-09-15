package helloJpa;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public Address() {}

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    // 값 타입 객체는 불변값으로 만들어야함.
    // 생성자에서 set하고 setter를 안만들거나 private setter로 한다.
    // 값을 복사하는게 아닌 값의 참조를 넘기기 때문에
    // 어느 한쪽에서 수정이 이러나면 양쪽이 변함.
    // 포인터 넣지....
    private void setStreet(String street) {
        this.street = street;
    }

    private String getZipcode() {
        return zipcode;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    // equals는 기본이 ==비교이기 때문에 오버라이딩해서 만들어야 함
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}