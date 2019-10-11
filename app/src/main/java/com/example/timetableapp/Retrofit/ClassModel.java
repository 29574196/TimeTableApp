package com.example.timetableapp.Retrofit;

    public class ClassModel
    {
        private String venue_id,time_slot,building,day_code,module_code,room;

        public String getTimeSlot() {
            return time_slot;
        }

        public void setTimeSlot(String timeSlot) {
            this.time_slot = timeSlot;
        }

        public String getBuilding() {
            return building;
        }

        public void setBuilding(String building) {
            this.building = building;
        }

        public String getDay() {
            return day_code;
        }

        public void setDay(String day) {
            this.day_code = day;
        }

        public String getModule() {
            return module_code;
        }

        public void setModule(String module) {
            this.module_code = module;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public String getVenue() {
            return venue_id;
        }

        public void setVenue(String venue) {
            this.venue_id = venue;
        }
    }

