CREATE TABLE IF NOT EXISTS public.child_info
(
    name     text PRIMARY KEY,
    address  text NOT NULL,
    behavior text NOT NULL
);

CREATE TABLE IF NOT EXISTS public.gift
(
    id         serial PRIMARY KEY,
    child_name text NOT NULL REFERENCES public.child_info (name),
    size       text NOT NULL
);

CREATE TABLE IF NOT EXISTS public.elf_info
(
    name    text PRIMARY KEY,
    address text NOT NULL
);

CREATE TABLE IF NOT EXISTS public.pack
(
    id     serial PRIMARY KEY,
    size     text    NOT NULL,
    type     text    NOT NULL
);

CREATE TABLE IF NOT EXISTS public.delivery_order
(
    id       serial PRIMARY KEY,
    gift_id  integer NOT NULL REFERENCES public.gift (id),
    pack_id integer NOT NULL REFERENCES public.pack (id),
    address  text    NOT NULL,
    state    text    NOT NULL,
    elf_name text REFERENCES public.elf_info (name)
);
