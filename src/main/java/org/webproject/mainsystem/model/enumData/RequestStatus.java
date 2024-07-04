package org.webproject.mainsystem.model.enumData;

public enum RequestStatus {
    ADMIN_PENDING(1),
    ADMIN_ACCEPTED(2),
    ADMIN_REJECTED(3),
    REPAIR_MAN_PENDING(4),
    REPAIR_MAN_ACCEPTED(5),
    DONE(6);

    private final int id;

    RequestStatus(int id) {
        this.id = id;
    }

    public RequestStatus getStatus(int id) {
        for (RequestStatus status : RequestStatus.values()) {
            if (status.id == id) { return status; }
        }
        return null;
    }



}
