package com.wiinvent.gami.domain.utils;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.wiinvent.gami.domain.utils.DateUtils.ZONE_VN;

@Log4j2
public class Helper {
  public static String ZONE_UTC = "UTC";

  public static String ZONE_DEFAULT = "Asia/Ho_Chi_Minh";
  static Random random = new Random();
  public static Map<String, Object> convertObjectToMap(Object object){
    try{
      Map<String, Object> map = new HashMap<>();
      Class<?> clazz = object.getClass();

      for (Field field : clazz.getDeclaredFields()) {
        field.setAccessible(true);
        String fieldName = field.getName();
        Object fieldValue = field.get(object);
        map.put(fieldName, fieldValue);
      }

      return map;
    }catch (Exception ex){
      log.error("=========>convertObjectToMap: ", ex);
      return new HashMap<>();
    }
  }

  // Chuyển Map về đối tượng
  public static <T> T convertMapToObject(Map<String, Object> map, Class<T> clazz) throws IllegalAccessException, InstantiationException {
    T object = clazz.newInstance();

    for (Field field : clazz.getDeclaredFields()) {
      field.setAccessible(true);
      String fieldName = field.getName();
      if (map.containsKey(fieldName)) {
        Object fieldValue = map.get(fieldName);
        field.set(object, fieldValue);
      }
    }

    return object;
  }


  public static long numberAround(long number1, long number2){
    double processQuantity = Math.ceil((double) number1 / number2);
    return (long) processQuantity;
  }

  public static long randomBetween(int min, int max) {
    return random.nextInt(min, max + 1);
  }

  public static Long startOfDaytoLong(LocalDate dateTime) {
    return dateTime.atTime(LocalTime.MIN).atZone(ZoneId.of(ZONE_DEFAULT)).withZoneSameInstant(ZoneId.of(ZONE_UTC)).toInstant().toEpochMilli();
  }

  public static Long endOfDaytoLong(LocalDate dateTime) {
    return dateTime.atTime(LocalTime.MAX).atZone(ZoneId.of(ZONE_DEFAULT)).withZoneSameInstant(ZoneId.of(ZONE_UTC)).toInstant().toEpochMilli();
  }

  public static Long getNowMillisAtUtc() {
    return System.currentTimeMillis();
  }

  public static String boDauTiengViet(String str) {
    str = str.replaceAll("à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ", "a");
    str = str.replaceAll("è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ", "e");
    str = str.replaceAll("ì|í|ị|ỉ|ĩ", "i");
    str = str.replaceAll("ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ", "o");
    str = str.replaceAll("ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ", "u");
    str = str.replaceAll("ỳ|ý|ỵ|ỷ|ỹ", "y");
    str = str.replaceAll("đ", "d");

    str = str.replaceAll("À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ", "A");
    str = str.replaceAll("È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ", "E");
    str = str.replaceAll("Ì|Í|Ị|Ỉ|Ĩ", "I");
    str = str.replaceAll("Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ", "O");
    str = str.replaceAll("Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ", "U");
    str = str.replaceAll("Ỳ|Ý|Ỵ|Ỷ|Ỹ", "Y");
    str = str.replaceAll("Đ", "D");
    return str;
  }

  public static String stringFormatDay(int value) {
    if (value < 10) return "0" + value;
    return value + "";
  }

  public static LocalDateTime getNowDateTimeAtVn() {
    return java.time.LocalDateTime.now(ZoneId.of(ZONE_DEFAULT));
  }

  public static LocalDateTime convertFromVnToUtc(LocalDateTime dateTimeAtVn) {
    return dateTimeAtVn
        .atZone(ZoneId.of(ZONE_DEFAULT))
        .withZoneSameInstant(ZoneId.of(ZONE_UTC))
        .toLocalDateTime();
  }
}
