/*
 * Copyright 2009-2012 Prime Teknoloji.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.examples.view;

import java.util.Date;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.primefaces.examples.validate.Email;

public class ValidationBean {
    
    private String text;
    
    private String description;
    
    private Integer integer;
    
    private Double doubleNumber;
    
    private Float floatNumber;
    
    private Double money;
    
    private String regexText;
       
    private Date date;
    
    @Null
    @Size(max=5)
    private String name;
    
    @Min(10) @Max(20)
    private Integer age;
    
    @DecimalMax("99.9")
    private Double amount;
    
    @Email(message = "must be a valid email")
    private String email;

    @AssertTrue
    private boolean assertControl;
    
    @Digits(integer = 2, fraction = 1)
    private String digitsControl;

    @Past
    private Date dateControlPast;
    
    @Future
    private Date dateControlFuture;
    
    @Past
    private Date dateControlPopup;
    
    @Pattern(regexp = "/^[-+]?\\d+$/") // integer
    private String pattern;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public Double getDoubleNumber() {
        return doubleNumber;
    }

    public void setDoubleNumber(Double doubleNumber) {
        this.doubleNumber = doubleNumber;
    }

    public Float getFloatNumber() {
        return floatNumber;
    }

    public void setFloatNumber(Float floatNumber) {
        this.floatNumber = floatNumber;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getRegexText() {
        return regexText;
    }

    public void setRegexText(String regexText) {
        this.regexText = regexText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAssertControl() {
        return assertControl;
    }

    public void setAssertControl(boolean assertControl) {
        this.assertControl = assertControl;
    }

    public String getDigitsControl() {
        return digitsControl;
    }

    public void setDigitsControl(String digitsControl) {
        this.digitsControl = digitsControl;
    }

    public Date getDateControlPast() {
        return dateControlPast;
    }

    public void setDateControlPast(Date dateControlPast) {
        this.dateControlPast = dateControlPast;
    }

    public Date getDateControlFuture() {
        return dateControlFuture;
    }

    public void setDateControlFuture(Date dateControlFuture) {
        this.dateControlFuture = dateControlFuture;
    }

    public Date getDateControlPopup() {
        return dateControlPopup;
    }

    public void setDateControlPopup(Date dateControlPopup) {
        this.dateControlPopup = dateControlPopup;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
