package com.golab.swimteach.model;

public enum UnitEnum {
    METERS {
        @Override
        public String toString() {
            return "m";
        }
    },
    REPS {
        @Override
        public String toString() {
            return "reps";
        }
    }
}
