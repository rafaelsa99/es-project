import axios from "axios"

const OPEN_SKY_API_BASE_URL = "http://localhost:8080/";
const OPEN_SKY_KAFKA_BASE_URL = "http://localhost:8081/";

class PlaneService{
    getPlanes(){
        return axios.get(OPEN_SKY_API_BASE_URL + "planes");
    }

    getHistory(){
        return axios.get(OPEN_SKY_API_BASE_URL + "history");
    }

    getEvents(){
        return axios.get(OPEN_SKY_KAFKA_BASE_URL + "events");
    }
}

export default new PlaneService();
