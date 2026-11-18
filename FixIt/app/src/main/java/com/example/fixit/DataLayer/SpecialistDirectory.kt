package com.example.fixit.DataLayer

import com.example.fixit.DataLayer.RoomDB.Specialist

object SpecialistDirectory {
    val specialists = listOf(
        Specialist(
            id = "spec_1",
            name = "Dr. Alan Turing",
            expertise = "Computer Hardware & Systems",
            description = "Specializes in motherboard diagnostics, CPU/GPU thermal management, and enterprise server hardware restoration.",
            phone = "+1-555-019-8234",
            email = "alan.turing@fixit-support.org",
            officeAddress = "104 Innovation Way, Suite 400, Tech City, CA",
            profileUrl = "https://fixit-support.org/specialists/alan-turing"
        ),
        Specialist(
            id = "spec_2",
            name = "Grace Hopper",
            expertise = "Networking & Security",
            description = "Expert in enterprise routing, firewall configuration, VPN troubleshooting, and secure wireless mesh networks.",
            phone = "+1-555-014-4920",
            email = "grace.hopper@fixit-support.org",
            officeAddress = "250 Network Blvd, Floor 2, Cyber Valley, NY",
            profileUrl = "https://fixit-support.org/specialists/grace-hopper"
        ),
        Specialist(
            id = "spec_3",
            name = "Linus Torvalds",
            expertise = "Software Support & Kernels",
            description = "Master debugger for operating system stability, driver conflicts, memory leaks, and performance optimization.",
            phone = "+1-555-017-3388",
            email = "linus.torvalds@fixit-support.org",
            officeAddress = "500 OpenSource Way, Building B, Seattle, WA",
            profileUrl = "https://fixit-support.org/specialists/linus-torvalds"
        ),
        Specialist(
            id = "spec_4",
            name = "Ada Lovelace",
            expertise = "Mobile Devices & Applications",
            description = "Specialized in cross-platform mobile app crashes, synchronization failures, and OS integration issues.",
            phone = "+1-555-012-7711",
            email = "ada.lovelace@fixit-support.org",
            officeAddress = "789 Algorithm Ave, Austin, TX",
            profileUrl = "https://fixit-support.org/specialists/ada-lovelace"
        )
    )

    fun getSpecialist(id: String?): Specialist? {
        if (id == null) return null
        return specialists.find { it.id == id }
    }
}
