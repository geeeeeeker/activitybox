package com.uxiangtech.activitybox.sdk.context;

public interface Context<C extends Context<?>> {

  C addData(final String key, final Object value);

  Object getData(final String key);

  default Byte getDataAsByte(final String key) throws ClassCastException {
    return (Byte)this.getData(key);
  }

  default Boolean getDataAsBoolean(final String key) throws ClassCastException {
    return (Boolean)this.getData(key);
  }

  default Short getDataAsShort(final String key) throws ClassCastException {
    return (Short)this.getData(key);
  }

  default Integer getDataAsInt(final String key) throws ClassCastException {
    return (Integer)this.getData(key);
  }

  default Long getDataAsLong(final String key) throws ClassCastException {
    return (Long)this.getData(key);
  }

  default Float getDataAsFloat(final String key) throws ClassCastException {
    return (Float)this.getData(key);
  }

  default Double getDataAsDouble(final String key) throws ClassCastException {
    return (Double)this.getData(key);
  }

  default Number getDataAsNumber(final String key) throws ClassCastException {
    return (Number)this.getData(key);
  }

  default String getDataAsString(final String key) throws ClassCastException {
    return (String)this.getData(key);
  }
}
