package com.danilo.barbershop.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.danilo.barbershop.domain.value_object.Name;

public class Client {
    public final UUID id;
    public final Name name;

    public Client(Name name) throws Exception {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public SchedulingProcess schedule(Task task) {
        return new SchedulingProcess(this, task);
    }

    public class SchedulingProcess {
        private final Client client;
        private final Task task;

        private SchedulingProcess(Client client, Task task) {
            this.client = client;
            this.task = task;
        }

        public SchedulingProcessWithBarber with(Barber barber) {
            return new SchedulingProcessWithBarber(this.client, this.task, barber);
        }

        public class SchedulingProcessWithBarber {
            private final Client client;
            private final Task task;
            private final Barber barber;

            private SchedulingProcessWithBarber(Client client, Task task, Barber barber) {
                this.client = client;
                this.task = task;
                this.barber = barber;
            }

            public SchedulingProcessWithBarberAndStartAt startingAt(LocalDateTime startAt) {
                return new SchedulingProcessWithBarberAndStartAt(this.client, this.task, this.barber, startAt);
            }

            public class SchedulingProcessWithBarberAndStartAt {
                private final Client client;
                private final Task task;
                private final Barber barber;
                private final LocalDateTime startAt;

                public SchedulingProcessWithBarberAndStartAt(Client client, Task task, Barber barber,
                        LocalDateTime startAt) {
                    this.client = client;
                    this.task = task;
                    this.barber = barber;
                    this.startAt = startAt;
                }

                public Appointment build() {
                    return new Appointment(startAt, client, barber, task);
                }
            }
        }
    }
}
