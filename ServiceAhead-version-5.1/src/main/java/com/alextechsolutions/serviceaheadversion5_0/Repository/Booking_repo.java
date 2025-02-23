package com.alextechsolutions.serviceaheadversion5_0.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alextechsolutions.serviceaheadversion5_0.Model.BookingDetail;

@Repository
public interface Booking_repo extends JpaRepository<BookingDetail,Long>{


}
